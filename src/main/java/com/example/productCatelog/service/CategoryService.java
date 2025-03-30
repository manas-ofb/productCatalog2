package com.example.productCatelog.service;


import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<Category> getCategories();
}
