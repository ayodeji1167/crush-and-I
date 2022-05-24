package com.example.crushandi.service;

import com.example.crushandi.dto.request.CreatePostRequest;
import com.example.crushandi.entity.BlogPost;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogPostService {
    void createBlogPost(CreatePostRequest createPostRequest);

    void editPost(Long id, CreatePostRequest createPostRequest);

    void deletePostById(Long id);

    List<BlogPost> getAllPost();

    Page<BlogPost> paginatedBlog(int offSet, int pageSize, String category);

    BlogPost getBlogWithHighestView();

    void deleteAllPostByUserId(Long id);

    List<BlogPost> getAllPostByUserId(Long id);

    BlogPost getPostById(Long id);

    void uploadImage(MultipartFile multipartFile);

    Resource returnImage(String imageName);

}
