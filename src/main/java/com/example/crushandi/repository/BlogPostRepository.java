package com.example.crushandi.repository;

import com.example.crushandi.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    @Transactional
    void deleteBlogPostsByAppUserId(Long id);

    List<BlogPost> findAllByAppUser_Id(Long id);

    Page<BlogPost> findAllByCategory(Pageable pageable, String category);


}