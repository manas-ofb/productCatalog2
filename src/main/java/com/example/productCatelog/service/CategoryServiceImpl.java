package com.example.productCatelog.service;

import com.example.productCatelog.converter.CategoryConverter;
import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.entity.Category;
import com.example.productCatelog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category = categoryRepository.save(category);

        return CategoryConverter.convertToDTO(category);
    }

    @Override
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
