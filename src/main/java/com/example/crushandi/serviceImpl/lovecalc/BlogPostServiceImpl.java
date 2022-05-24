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
import java.util.List;
import java.util.Objects;

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
    public void editPost(Long id, CreatePostRequest createPostRequest) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        mapRequestToEntity(blogPost, createPostRequest);
        blogPost.setId(id);
        blogPostRepository.save(blogPost);
    }

    @Override
    public void deletePostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        blogPostRepository.delete(blogPost);
    }

    @Override
    public List<BlogPost> getAllPost() {
        return blogPostRepository.findAll();
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

    @Override
    public void deleteAllPostByUserId(Long id) {
        blogPostRepository.deleteBlogPostsByAppUserId(id);
    }

    @Override
    public List<BlogPost> getAllPostByUserId(Long id) {
        return blogPostRepository.findAllByAppUser_Id(id);
    }

    @Override
    public BlogPost getPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new BlogPostException("Post Not Found"));
        blogPost.setView(blogPost.getView() + 1);
        blogPostRepository.save(blogPost);
        return blogPost;
    }

    @Override
    public void uploadImage(MultipartFile multipartFile) {
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

    //UTIL METHODS
    private void mapRequestToEntity(BlogPost blogPost, CreatePostRequest createPostRequest) {
        blogPost.setTitle(createPostRequest.getTitle());
        blogPost.setCategory(createPostRequest.getCategory());
        blogPost.setContent(createPostRequest.getContent());
        blogPost.setImageName(createPostRequest.getImageName());

    }
}
