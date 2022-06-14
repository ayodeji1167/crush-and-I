package com.example.crushandi.controller;

import com.example.crushandi.dto.request.CommentDto;
import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.dto.request.ReplyDto;
import com.example.crushandi.dto.request.UnAuthorize;
import com.example.crushandi.entity.BlogPost;
import com.example.crushandi.entity.Comment;
import com.example.crushandi.service.BlogPostService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogPostService blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

//    //POPULATE BLOG DB
//    @GetMapping("/populate")
//    @Transactional
//    public void populateDbForTest() {
//        IntStream.rangeClosed(1, 40).forEach(i -> {
//            CreatePostRequest blogPost = new CreatePostRequest();
//            blogPost.setImageName("image");
//            blogPost.setCategory("all");
//            blogPost.setContent("This is the Blog Content");
//            blogPost.setTitle("TITLE");
//            blogPost.setUserId(1L);
//            blogPostService.createBlogPost(blogPost);
//        });
//    }

    @GetMapping("/get/all")
    public List<BlogPost> getAllPost() {
        return blogPostService.getAllPost();
    }


    @GetMapping("/get/page")
    public ResponseEntity<?> getPaginatedBlogs(@RequestParam("offSet") int offSet,
                                               @RequestParam("pageSize") int pageSize,
                                               @RequestParam("category") String category) {
        System.out.println("i was called");
        return ResponseEntity.ok(blogPostService.paginatedBlog(offSet, pageSize, category));

    }

    @GetMapping("/get/trending")
    public BlogPost getTrendingBlog() {
        return blogPostService.getBlogWithHighestView();
    }


    @GetMapping("/get/popular")
    public List<BlogPost> getPopularPost() {
        return blogPostService.getPopularPosts();
    }

    @PostMapping("/upload/image")
    public void uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        blogPostService.uploadImage(multipartFile);
    }

    @GetMapping("/image/get/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
        Resource resource = blogPostService.returnImage(imageName);

        MediaType mediaType = MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @GetMapping("/get/{id}")
    public BlogPost getPostById(@PathVariable String id) {
        return blogPostService.getPostById(id);
    }

    @GetMapping("/get/all/{id}")
    public List<BlogPost> getAllPostsById(@PathVariable String id) {
        return blogPostService.getAllPostByUserId(id);
    }


    @PostMapping("/save")
    public BlogPost savePost(@RequestBody CreatePostRequest createPostRequest) {
        return blogPostService.createBlogPost(createPostRequest);
    }

    @PutMapping("/edit/{id}")
    public void editPost(@PathVariable String id, @RequestBody @Valid CreatePostRequest createPostRequest) {

        blogPostService.editPost(id, createPostRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable String id) {
        blogPostService.deletePostById(id);
    }

    @DeleteMapping("/delete/all/{id}")
    public void deletePostsByUserId(@PathVariable String id) {
        blogPostService.deleteAllPostByUserId(id);
    }

    // CONTROLLER FOR REPLY AND COMMENTS;
    @PostMapping("/add/comment/{postId}")
    public BlogPost addComment(@PathVariable String postId, @RequestBody CommentDto commentDto) {
        return blogPostService.addComment(postId, commentDto.getName(), commentDto.getContent());
    }

    @GetMapping("/comments/getall")
    public List<Comment> getAllComments() {
        return blogPostService.getAllComments();
    }

    @PostMapping("/comment/unauthorize")
    public ResponseEntity<?> unauthorizeComment(@RequestBody UnAuthorize unAuthorize) {
        blogPostService.unAuthorize(unAuthorize.getReplyId(), unAuthorize.getCommentId(), unAuthorize.getPostId());
        return ResponseEntity.ok().body("Process Successful");
    }


    @PostMapping("/add/reply/{commentId}/{postId}")
    public BlogPost addReply(@PathVariable String postId, @RequestBody ReplyDto replyDto, @PathVariable String commentId) {
        return blogPostService.addReply(commentId, postId, replyDto.getName(), replyDto.getContent());
    }

    @DeleteMapping("/delete/comment")
    public BlogPost deleteReplyOrComment(@RequestBody UnAuthorize unAuthorize) {
        return blogPostService.deleteReplyOrComment(unAuthorize.getReplyId(), unAuthorize.getCommentId(), unAuthorize.getPostId());
    }

}
