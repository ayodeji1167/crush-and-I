package com.example.crushandi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reply {
    @Id
    private  String id;

    private String name;

    private String content;

    @JsonIgnore
    private String commentId;

    public Reply(String content, String name, String commentId) {
        this.content = content;
        this.name=name;
        this.commentId = commentId;
    }
}
