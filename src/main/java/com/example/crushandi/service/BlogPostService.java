package com.example.crushandi.service;

import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.entity.BlogPost;
import com.example.crushandi.entity.Comment;
import com.example.crushandi.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogPostService {
    BlogPost createBlogPost(CreatePostRequest createPostRequest);

    void editPost(String id, CreatePostRequest createPostRequest);

    void deletePostById(String id);

    List<BlogPost> getAllPost();

    List<BlogPost> getPopularPosts();

    Page<BlogPost> paginatedBlogByCategory(int offSet, int pageSize, String category);

    BlogPost getBlogWithHighestView();

    void deleteAllPostByUserId(String id);


    List<BlogPost> getAllPostByUserId(String id);

    BlogPost getPostById(String id);

    //IMAGE FUNCTIONALITY --------------------------------------------------------------

    String uploadImage(MultipartFile multipartFile);

    String deleteImage(String imageName);

    byte[] returnImage(String imageName);

    //REPLY AND COMMENT -----------------------------------------------------------------------------------------------
    BlogPost addComment(String postId, String name, String content);

    List<Comment> getAllComments();

    BlogPost deleteReplyOrComment(String replyId, String commentId, String postId);

    BlogPost addReply(String commentId, String postId, String name, String content);

    List<Reply> getAllReplies();

    void unAuthorize(String replyId, String commentId, String postId);
}
