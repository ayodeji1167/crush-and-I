package com.example.crushandi.service;

import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.entity.BlogPost;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogPostService {
    void createBlogPost(CreatePostRequest createPostRequest);

    void editPost(String id, CreatePostRequest createPostRequest);

    void deletePostById(String id);

    List<BlogPost> getAllPost();

    List<BlogPost> getPopularPosts();

    Page<BlogPost> paginatedBlog(int offSet, int pageSize, String category);

    BlogPost getBlogWithHighestView();

    void deleteAllPostByUserId(String id);

    List<BlogPost> getAllPostByUserId(String id);

    BlogPost getPostById(String id);

    void uploadImage(MultipartFile multipartFile);

    Resource returnImage(String imageName);

    BlogPost addComment(String postId, String name, String content);

}
