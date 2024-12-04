package com.solidvessel.payment.adapter.out.product.db.entity;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductEmbeddable {

    private Long productId;
    private String name;
    private Double price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private Integer quantity;

    public static ProductEmbeddable from(Product product) {
        return new ProductEmbeddable(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getQuantity());
    }

    public Product toDomainModel() {
        return new Product(productId, name, price, category, quantity);
    }
}
