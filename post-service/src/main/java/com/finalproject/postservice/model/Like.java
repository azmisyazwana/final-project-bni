package com.finalproject.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("likes")
public class Like {
    @Id
    private String likeId;
    private Long userId;
    private String postId;
}
