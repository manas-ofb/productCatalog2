package com.example.productCatelog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static interface NameStep {
        PriceStep withName(String name);
    }

    public static interface PriceStep {
        CategoryStep withPrice(BigDecimal price);
    }

    public static interface CategoryStep {
        BuildStep withCategory(Category category);
    }

    public static interface BuildStep {
        Product build();
    }

    public static class Builder implements NameStep, PriceStep, CategoryStep, BuildStep {
        private String name;

        private BigDecimal price;

        private Category category;

        private Builder() {
        }

        public static NameStep product() {
            return new Builder();
        }

        @Override
        public PriceStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CategoryStep withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        @Override
        public BuildStep withCategory(Category category) {
            this.category = category;
            return this;
        }

        @Override
        public Product build() {
            return new Product(
                this.name,
                this.price,
                this.category
            );
        }
    }
}
