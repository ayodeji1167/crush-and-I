package com.example.crushandi.serviceImpl.lovecalc;

import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.entity.BlogPost;
import com.example.crushandi.exception.BlogPostException;
import com.example.crushandi.repository.AppUserRepository;
import com.example.crushandi.repository.BlogPostRepository;
import com.example.crushandi.service.BlogPostService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final AppUserRepository appUserRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, AppUserRepository appUserRepository) {
        this.blogPostRepository = blogPostRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void createBlogPost(CreatePostRequest createPostRequest) {
        BlogPost blogPost = new BlogPost();
        blogPost.setAppUser(appUserRepository.findById(createPostRequest.getUserId()).get());
        mapRequestToEntity(blogPost, createPostRequest);

        blogPostRepository.save(blogPost);
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
        for (BlogPost p : blogPosts){
            top3Post.add(p);
            i++;
            if(i == 3) break;
        }
        return top3Post;
    }

    public Page<BlogPost> paginatedBlog(int offSet, int pageSize, String category) {
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

    public List<BlogPost> getRelatedPost(String blogPostId) {
        //First Get The Currently Viewed Blog Post
        BlogPost blogPost = blogPostRepository.findById(blogPostId).get();

        //Create a List for related post
        List<BlogPost> relatedPost = new ArrayList<>();

        //Now Loop through all the post in the currentPost category
       List<BlogPost> allThePostInTheCategoryOfThePost =  blogPostRepository.findByCategory(blogPost.getCategory());

      for (BlogPost p : allThePostInTheCategoryOfThePost){
          if(relatedPost.size() == 3) break; //If the related post array don reach 3, e go leave the loop
          if (p == blogPost) continue; //if the  currently looped post na the viewed one, e go skip the add (below) and go up again
          relatedPost.add(p);
      }

      return relatedPost;
    }

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

    @Override
    public void uploadImage(MultipartFile multipartFile ) {
        try {
            String imageDirectory = "./src/main/resources/static/" + StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Path uploadDir = Paths.get(imageDirectory);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, uploadDir, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Resource returnImage(String imageName) {
        Path path = Paths.get("./src/main/resources/static/").toAbsolutePath().resolve(imageName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to get file " + e.getMessage());
        }
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Resource is  unable to read file");
        }
        return resource;

    }

    @Override
    public BlogPost addComment(String postId, String name, String content) {
        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
        blogPost.addComment(name, content);
        return blogPostRepository.save(blogPost);
    }

    //UTIL METHODS
    private void mapRequestToEntity(BlogPost blogPost, CreatePostRequest createPostRequest) {
        blogPost.setTitle(createPostRequest.getTitle());
        blogPost.setCategory(createPostRequest.getCategory());
        blogPost.setContent(createPostRequest.getContent());
        blogPost.setMainImage(createPostRequest.getImageName());

        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));

        blogPost.setCreatedDate(formattedDate);
        blogPost.setUpdatedDate(formattedDate);
    }
}
