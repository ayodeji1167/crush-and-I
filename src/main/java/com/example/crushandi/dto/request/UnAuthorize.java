package com.example.crushandi.dto.request;

import lombok.Data;

@Data
public class UnAuthorize {
    private String commentId;
    private String postId;
    private String replyId;
}
