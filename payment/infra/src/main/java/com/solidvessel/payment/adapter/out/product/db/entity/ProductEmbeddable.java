package com.solidvessel.payment.adapter.out.product.db.entity;

import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.payment.product.model.ProductSubcategory;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEmbeddable {

    public ProductEmbeddable(Long productId, String name, Double price, ProductCategory category, Integer quantity) {
        this(productId, name, price, category, null, quantity);
    }

    private Long productId;
    private String name;
    private Double price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @Enumerated(EnumType.STRING)
    private ProductSubcategory subcategory;
    private Integer quantity;
}
