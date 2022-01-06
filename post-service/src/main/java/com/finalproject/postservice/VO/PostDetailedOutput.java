package com.finalproject.postservice.VO;

import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailedOutput {
    private String postId;
    private String title;
    private String content;
    private Category category;
    private User user;
    private Integer likeCounts;
    private List<Comment> commentOnPostList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
