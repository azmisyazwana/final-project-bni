package com.finalproject.postservice.VO;

import com.finalproject.postservice.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentOutput {
    private String commentId;
    private String contentComment;
    private Post post;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
