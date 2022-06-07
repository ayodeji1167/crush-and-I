package com.example.crushandi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Comment {
    @Id
    private String id;

    private String name;

    private String content;

    @JsonIgnore
    private String postId;

    private List<Reply> reply = new ArrayList<>();



    public Comment(String name, String content, String postId) {
        this.name = name;
        this.content = content;
        this.postId = postId;
    }
}
