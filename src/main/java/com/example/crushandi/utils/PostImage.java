package com.example.crushandi.utils;

import com.example.crushandi.entity.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "blogId")
    private BlogPost blogPost;

    public PostImage(String imageName, BlogPost blogPost) {
        this.name = imageName;
        this.blogPost = blogPost;
    }
}
