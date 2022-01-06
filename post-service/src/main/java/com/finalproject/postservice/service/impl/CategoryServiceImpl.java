package com.finalproject.postservice.service.impl;

import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.repository.CategoryRepository;
import com.finalproject.postservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        if(categoryRepository.findById(category.getCategoryId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories;
    }

    @Override
    public Category updateCategory(Category category, String id) {
        Category categoryUpdate = this.getCategory(id);
        categoryUpdate.setCategory(category.getCategory());
        categoryRepository.save(categoryUpdate);
        return categoryUpdate;
    }

    @Override
    public void deleteCategory(String id) {
        this.categoryRepository.deleteById(id);
    }
}
