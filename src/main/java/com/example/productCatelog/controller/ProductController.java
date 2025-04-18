package com.example.productCatelog.controller;

import com.example.productCatelog.Annotation.CheckPermission;
import com.example.productCatelog.Enum.Permission;
import com.example.productCatelog.dto.ProductDto;
import com.example.productCatelog.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @CheckPermission(Permission.WRITE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping
    @CheckPermission(Permission.READ)
    public Page<ProductDto> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    @CheckPermission(Permission.READ)
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
