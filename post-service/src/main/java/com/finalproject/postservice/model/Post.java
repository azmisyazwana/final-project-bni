package com.finalproject.postservice.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document("posts")
public class Post {
    @Id
//    @Field(name = "post_id")
    private String postId;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

//    @Field(name = "like_count")
    private Integer likeCount = 0;

    private String categoryId;

//    @Field(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Long userId;
}

//{
//    "post":{
//        "postId":"..."
//        "title":"...",
//        "content":"...",
//        "userId":"...",
//        "like":[
//        {
//            "userId": "..."
//        },
//        {
//            "userId": "...."
//        }
//                ],
//        {"comment":[
//                "commentId":"..."
//                ],
//
//        }
//        }
//        }