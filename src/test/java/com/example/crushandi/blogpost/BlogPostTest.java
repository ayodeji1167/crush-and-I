package com.example.crushandi.blogpost;

import com.example.crushandi.entity.BlogPost;
import com.example.crushandi.repository.BlogPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BlogPostTest {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSavePostWithImages(){

        Long postId = 1L;
        BlogPost blogPost = blogPostRepository.findById(postId).get();

        blogPost.setMainImage("main_image.jpg");

        blogPost.addExtraImages("extra image1.png");
        blogPost.addExtraImages("extra image2.png");
        blogPost.addExtraImages("extra image3.png");

        blogPostRepository.save(blogPost);

    }


























}
