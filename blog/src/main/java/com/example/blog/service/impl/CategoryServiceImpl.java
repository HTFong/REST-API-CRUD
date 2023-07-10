package com.example.blog.service.impl;

import com.example.blog.dtos.CategoryDto;
import com.example.blog.entity.Category;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category categoryEntity = modelMapper.map(categoryDto, Category.class);
        Category rs = categoryRepo.save(categoryEntity);
        categoryDto = modelMapper.map(rs, CategoryDto.class);
        return categoryDto;
    }
    @Override
    public CategoryDto getCategory(long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
        CategoryDto dto = modelMapper.map(category, CategoryDto.class);
        return dto;
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepo.findAll();
        List<CategoryDto> dtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);

    }
    @Override
    public void deleteCategory(long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
        categoryRepo.delete(category);
    }
}
