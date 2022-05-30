package com.example.crushandi.repository;

import com.example.crushandi.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {
    @Transactional
    void deleteBlogPostsByAppUserId(String id);

    List<BlogPost> findAllByAppUser_Id(String id);

    Page<BlogPost> findAllByCategory(Pageable pageable, String category);


}