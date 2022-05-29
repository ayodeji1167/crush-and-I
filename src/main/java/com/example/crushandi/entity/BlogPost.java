package com.example.crushandi.entity;

import com.example.crushandi.utils.PostImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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

    @OneToMany(mappedBy = "blogPost" , cascade = CascadeType.PERSIST)
    private Set<PostImage> images = new HashSet<>();

    @NotBlank
    private String category;

    private int view;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"role", "email", "password"})
    private AppUser appUser;

    private String createdDate;

    private String updatedDate;


    public void addExtraImages(String imageName){
        this.images.add(new PostImage(imageName,this));
    }

}
