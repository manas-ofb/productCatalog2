package com.example.productCatelog.controller;

import com.example.productCatelog.Annotation.CheckPermission;
import com.example.productCatelog.Enum.Permission;
import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.repository.CategoryRepository;
import com.example.productCatelog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService,
        CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @CheckPermission(Permission.WRITE)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping
    @CheckPermission(Permission.READ)
    public Page<CategoryDto> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }

    @GetMapping("/{id}")
    @CheckPermission(Permission.READ)
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
