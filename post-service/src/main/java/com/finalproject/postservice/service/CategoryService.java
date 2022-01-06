package com.finalproject.postservice.service;

import com.finalproject.postservice.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(String id);
    List<Category> getAllCategories();
    Category updateCategory(Category category, String id);
    void deleteCategory(String id);
}
