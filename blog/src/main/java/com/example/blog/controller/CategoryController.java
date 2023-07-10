package com.example.blog.controller;

import com.example.blog.dtos.CategoryDto;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCate(@RequestBody CategoryDto category) {
        CategoryDto categoryDto = categoryService.addCategory(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCate(@PathVariable("id") long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCate(@RequestBody CategoryDto categoryDto, @PathVariable("id") long categoryId) {
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<String> deleteCate(@PathVariable("id") long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Cate delete success");
    }
}
