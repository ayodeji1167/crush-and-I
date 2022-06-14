package com.example.crushandi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reply {
    @Id
    private String id;

    private String name;

    private String content;

    private String commentId;

    private LocalDateTime createdAt;

    private boolean authorize;

    public Reply(String content, String name, String commentId, boolean authorize) {
        this.content = content;
        this.name = name;
        this.commentId = commentId;
        this.authorize = authorize;
    }
}
