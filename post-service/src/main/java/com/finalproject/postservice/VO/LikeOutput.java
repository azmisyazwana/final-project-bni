package com.finalproject.postservice.VO;

import com.finalproject.postservice.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeOutput {
    private String likeId;
    private User user;
    private Post post;
}
