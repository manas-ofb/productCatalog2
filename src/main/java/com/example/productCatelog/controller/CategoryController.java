package com.example.productCatelog.controller;

import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.dto.ProductDto;
import com.example.productCatelog.entity.Category;
import com.example.productCatelog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

}
