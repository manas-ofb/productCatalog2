package com.example.productCatelog.converter;


import com.example.productCatelog.dto.ProductDto;
import com.example.productCatelog.entity.Product;

public class ProductConverter {

    public static ProductDto convertToDTO(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getCategory().getId());
    }

}
