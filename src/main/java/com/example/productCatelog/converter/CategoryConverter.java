package com.example.productCatelog.converter;

import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.entity.Category;

public class CategoryConverter {
    public static CategoryDto convertToDTO(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
