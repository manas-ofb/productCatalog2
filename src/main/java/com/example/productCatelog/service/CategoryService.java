package com.example.productCatelog.service;

import com.example.productCatelog.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    Page<CategoryDto> getCategories(Pageable pageable);
}
