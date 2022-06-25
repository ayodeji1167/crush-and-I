package com.example.crushandi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
public class BlogPost {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String mainImage;

    @JsonIgnoreProperties(value = {"post"})
    private List<Comment> comments = new ArrayList<>();

    @NotBlank
    private String category;

    private String tags;

    private int view;

    @JsonIgnoreProperties(value = {"role", "email", "password"})
    private AppUser appUser;

    private String createdDate;

    private String updatedDate;



}
