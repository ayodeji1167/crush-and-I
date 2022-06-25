package com.example.crushandi.serviceImpl.lovecalc;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.entity.BlogPost;
import com.example.crushandi.entity.Comment;
import com.example.crushandi.entity.Reply;
import com.example.crushandi.exception.BlogPostException;
import com.example.crushandi.repository.AppUserRepository;
import com.example.crushandi.repository.BlogPostRepository;
import com.example.crushandi.service.BlogPostService;
import com.example.crushandi.utils.BadWordsCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final AppUserRepository appUserRepository;
    private final BadWordsCheck badWordsCheck;
    private final AmazonS3 amazonS3;
    @Value("${bucketname}")
    private String bucketname;


    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, AppUserRepository appUserRepository, BadWordsCheck badWordsCheck, AmazonS3 amazonS3) {
        this.blogPostRepository = blogPostRepository;
        this.appUserRepository = appUserRepository;

        this.badWordsCheck = badWordsCheck;
        this.amazonS3 = amazonS3;
    }

    @Override
    public BlogPost createBlogPost(CreatePostRequest createPostRequest) {
        BlogPost blogPost = new BlogPost();
        blogPost.setAppUser(appUserRepository.findById(createPostRequest.getUserId()).get());
        mapRequestToEntity(blogPost, createPostRequest);

        return blogPostRepository.save(blogPost);
    }

    @Override
    public void editPost(String id, CreatePostRequest createPostRequest) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        mapRequestToEntity(blogPost, createPostRequest);
        blogPost.setId(id);
        blogPostRepository.save(blogPost);
    }

    @Override
    public void deletePostById(String id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        blogPostRepository.delete(blogPost);
    }

    @Override
    public List<BlogPost> getAllPost() {
        return blogPostRepository.findAll();
    }

    @Override
    public List<BlogPost> getPopularPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll(Sort.by("view").descending());
        List<BlogPost> top3Post = new ArrayList<>();
        int i = 0;
        for (BlogPost p : blogPosts) {
            top3Post.add(p);
            i++;
            if (i == 3) break;
        }
        return top3Post;
    }

    public Page<BlogPost> paginatedBlogByCategory(int offSet, int pageSize, String category) {
        Pageable pageable = PageRequest.of(offSet, pageSize, Sort.by("id").descending());
        if (!category.equals("all")) {
            return blogPostRepository.findAllByCategory(pageable, category);
        }
        return blogPostRepository.findAll(pageable);
    }

    @Override
    public BlogPost getBlogWithHighestView() {
        List<BlogPost> blogPosts = blogPostRepository.findAll(Sort.by("view").descending());
        return blogPosts.get(0);
    }

//    public List<BlogPost> getRelatedPost(String blogPostId) {
//        //First Get The Currently Viewed Blog Post
//        BlogPost blogPost = blogPostRepository.findById(blogPostId).get();
//
//        //Create a List for related post
//        List<BlogPost> relatedPost = new ArrayList<>();
//
//        //Now Loop through all the post in the currentPost category
//       List<BlogPost> allThePostInTheCategoryOfThePost =  blogPostRepository.findByCategory(blogPost.getCategory());
//
//      for (BlogPost p : allThePostInTheCategoryOfThePost){
//          if(relatedPost.size() == 3) break; //If the related post array don reach 3, e go leave the loop
//          if (p == blogPost) continue; //if the  currently looped post na the viewed one, e go skip the add (below) and go up again
//          relatedPost.add(p);
//      }
//
//      return relatedPost;
//    }

    @Override
    public void deleteAllPostByUserId(String id) {
        blogPostRepository.deleteBlogPostsByAppUserId(id);
    }

    @Override
    public List<BlogPost> getAllPostByUserId(String id) {
        return blogPostRepository.findAllByAppUser_Id(id);
    }

    @Override
    public BlogPost getPostById(String id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        blogPost.setView(blogPost.getView() + 1);
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    //IMAGE FUNCTIONALITIES ==============================================================================
    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        try {
            File file = convertMultipartToFile(multipartFile);
            PutObjectResult putObjectResult = amazonS3.putObject(bucketname, fileName, file);
            file.delete();
            return putObjectResult.getContentMd5();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public String deleteImage(String imageName) {
        amazonS3.deleteObject(bucketname,imageName);
        return "Object With Name " + imageName+ " Deleted";
    }

    @Override
    public byte[] returnImage(String imageName) {
//        Path path = Paths.get("./src/main/resources/static/").toAbsolutePath().resolve(imageName);
//        Resource resource;
//        try {
//            resource = new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Unable to get file " + e.getMessage());
//        }
//        if (!resource.exists() || !resource.isReadable()) {
//            throw new RuntimeException("Resource is  unable to read file");
//        }
//        return resource;
        S3Object s3Object = amazonS3.getObject(bucketname,imageName);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    //COMMENTS AND REPLY METHODS=================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public BlogPost addComment(String postId, String name, String content) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setPostName(blogPost.getTitle());
        comment.setAuthorize(true);
        comment.setName(name);
        comment.setContent(content);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setPostId(postId);
        if (badWordsCheck.isBadWordPresent(content)) {
            comment.setAuthorize(false);
        }
        List<Comment> listOfComment = blogPost.getComments();
        listOfComment.add(comment);
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    @Override
    public List<Comment> getAllComments() {
        List<BlogPost> allBlogs = blogPostRepository.findAll();
        return allBlogs.stream().map(BlogPost::getComments).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public BlogPost deleteReplyOrComment(String replyId, String commentId, String postId) {
        if (replyId.equals("null")) {
            return deleteComment(commentId, postId);
        } else {
            return deleteReply(replyId, commentId, postId);
        }
    }


    @Override
    public BlogPost addReply(String commentId, String postId, String name, String content) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        Comment comment = null;

        //TO GET THE COMMENT
        for (Comment comment1 : blogPost.getComments()) {
            if (comment1.getId().equals(commentId)) {
                comment = comment1;
                break;
            }
        }

        //ADD REPLY TO THE COMMENT
        if (!(comment == null)) {
            Reply reply = new Reply(content, name, commentId, true);
            reply.setId(UUID.randomUUID().toString());
            reply.setCreatedAt(LocalDateTime.now());
            if (badWordsCheck.isBadWordPresent(content)) {
                reply.setAuthorize(false);
            }
            comment.getReply().add(reply);
        }
        return blogPostRepository.save(blogPost);
    }

    @Override
    public List<Reply> getAllReplies() {
        List<BlogPost> allBlogs = blogPostRepository.findAll();

        List<Comment> commentList = allBlogs.stream().map(BlogPost::getComments).flatMap(Collection::stream).collect(Collectors.toList());

        return commentList.stream().map(Comment::getReply).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public void unAuthorize(String replyId, String commentId, String postId) {
        if (replyId.equals("null")) {
            unAuthorizeComment(commentId, postId);
        } else {
            unAuthorizeReply(replyId, commentId, postId);
        }

    }


    //UTIL METHODS --------------------------------------------------------------------------------------------------------
    private void mapRequestToEntity(BlogPost blogPost, CreatePostRequest createPostRequest) {
        blogPost.setTitle(createPostRequest.getTitle());
        blogPost.setCategory(createPostRequest.getCategory());
        blogPost.setContent(createPostRequest.getContent());
        blogPost.setMainImage(createPostRequest.getImageName());
        blogPost.setTags(createPostRequest.getTags());

        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));

        blogPost.setCreatedDate(formattedDate);
        blogPost.setUpdatedDate(formattedDate);
    }

    private void unAuthorizeComment(String commentId, String postId) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        for (Comment comment : blogPost.getComments()) {
            if (comment.getId().equals(commentId)) {
                comment.setAuthorize(!comment.isAuthorize());
            }
        }
        blogPostRepository.save(blogPost);
    }

    private void unAuthorizeReply(String replyId, String commentId, String postId) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        Comment comment = null;

        //TO GET THE COMMENT
        for (Comment comment1 : blogPost.getComments()) {
            if (comment1.getId().equals(commentId)) {
                comment = comment1;
                break;
            }
        }

        //GET REPLIES IN THE COMMENT
        if (comment != null) {
            List<Reply> replies = comment.getReply();

            //CHECK FOR THE REPLY
            for (Reply reply : replies) {
                if (reply.getId().equals(replyId)) {
                    System.out.println(reply.isAuthorize());
                    reply.setAuthorize(!reply.isAuthorize());
                }
            }
        }
        blogPostRepository.save(blogPost);
    }

    private BlogPost deleteComment(String commentId, String postId) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post with id " + postId + " not  Found"));
        List<Comment> commentList = blogPost.getComments();
        List<Comment> newComments = commentList.stream().filter(comment -> !comment.getId().equals(commentId)).collect(Collectors.toList());
        blogPost.setComments(newComments);
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    private BlogPost deleteReply(String replyId, String commentId, String postId) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        List<Comment> commentList = blogPost.getComments();
        for (Comment comment : commentList) {
            if (comment.getId().equals(commentId)) {
                List<Reply> newReplies = comment.getReply().stream().filter(reply -> !reply.getId().equals(replyId)).collect(Collectors.toList());
                comment.setReply(newReplies);
            }
        }
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }


}