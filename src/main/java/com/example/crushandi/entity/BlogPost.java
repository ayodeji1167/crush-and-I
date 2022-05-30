package com.example.crushandi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Lob
    private String content;

    @Column(nullable = false )
    private String mainImage;

//    @OneToMany(mappedBy = "blogPost" , cascade = CascadeType.ALL)
//    private Set<PostImage> images = new HashSet<>();

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @NotBlank
    private String category;

    private int view;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"role", "email", "password"})
    private AppUser appUser;

    private String createdDate;

    private String updatedDate;

//
//    public void addExtraImages(String imageName){
//        this.images.add(new PostImage(imageName,this));
//    }

    public void addComment(String name, String content){
        this.comments.add(new Comment(name,content,this));
    }

}
