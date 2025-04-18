package com.example.productCatelog.service;

import com.example.productCatelog.converter.CategoryConverter;
import com.example.productCatelog.dto.CategoryDto;
import com.example.productCatelog.entity.Category;
import com.example.productCatelog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category = categoryRepository.save(category);

        return CategoryConverter.convertToDTO(category);
    }

    @Override
    public Page<CategoryDto> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(CategoryConverter::convertToDTO);
    }

    @Override
    @Cacheable(cacheNames = "categoryDtoCache", key = "#id")
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("category not found"));
        return CategoryConverter.convertToDTO(category);
    }
}
