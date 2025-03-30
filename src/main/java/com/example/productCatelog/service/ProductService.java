package com.example.productCatelog.service;

import com.example.productCatelog.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    Page<ProductDto> getProducts(Pageable pageable);
    ProductDto getProductById(Long id);
}
