package com.finalproject.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("categories")
public class Category {
    @Id
    private String categoryId;
    private String category;
}
