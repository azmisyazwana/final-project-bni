package com.finalproject.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Document("comments")
public class Comment {
    @Id
    private String commentId;
    private String postId;
    private String contentComment;
    private Long userId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
