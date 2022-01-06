package com.finalproject.postservice.controller;

import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category req){
        Category categoryCreated = categoryService.createCategory(req);
        return ResponseEntity.ok(categoryCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id){
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable String id){
        Category categoryUpdated = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(categoryUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id){
       categoryService.deleteCategory(id);
       return ResponseEntity.ok(Boolean.TRUE);
    }
}
