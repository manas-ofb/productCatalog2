package com.example.productCatelog.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private Long categoryId;

    public static interface IdStep {
        NameStep withId(Long id);
    }

    public static interface NameStep {
        PriceStep withName(String name);
    }

    public static interface PriceStep {
        CategoryIdStep withPrice(BigDecimal price);
    }

    public static interface CategoryIdStep {
        BuildStep withCategoryId(Long categoryId);
    }

    public static interface BuildStep {
        ProductDto build();
    }

    public static class Builder implements IdStep, NameStep, PriceStep, CategoryIdStep, BuildStep {
        private Long id;

        private String name;

        private BigDecimal price;

        private Long categoryId;

        private Builder() {
        }

        public static IdStep productDto() {
            return new Builder();
        }

        @Override
        public NameStep withId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public PriceStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CategoryIdStep withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        @Override
        public BuildStep withCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        @Override
        public ProductDto build() {
            return new ProductDto(
                this.id,
                this.name,
                this.price,
                this.categoryId
            );
        }
    }
}
