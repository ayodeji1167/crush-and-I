package com.example.crushandi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String id;

    private String name;

    private String content;

    private String postId;

    private String postName;

    private boolean authorized;


    private LocalDateTime createdDate;


    private List<Reply> reply = new ArrayList<>();


    public Comment(String name, String content, String postId) {
        this.name = name;
        this.content = content;
        this.postId = postId;
    }
}
