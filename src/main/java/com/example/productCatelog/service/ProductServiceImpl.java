package com.example.productCatelog.service;

import com.example.productCatelog.converter.ProductConverter;
import com.example.productCatelog.dto.ProductDto;
import com.example.productCatelog.entity.Category;
import com.example.productCatelog.entity.Product;
import com.example.productCatelog.repository.CategoryRepository;
import com.example.productCatelog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
        CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.Builder.product()
            .withName(productDto.getName())
            .withPrice(productDto.getPrice())
            .withCategory(category)
            .build();

        productRepository.save(product);

        return ProductConverter.convertToDTO(product);
    }

    @Override
    public Page<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductConverter::convertToDTO);
    }

    @Override
    @Cacheable(cacheNames = "productDtoCache", key = "#id")
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductConverter.convertToDTO(product);
    }
}