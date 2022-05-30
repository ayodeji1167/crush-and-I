package com.example.crushandi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPost post;

    public Comment(String name, String content,BlogPost blogPost){
        this.name = name;
        this.content = content;
        this.post = blogPost;
    }
}
