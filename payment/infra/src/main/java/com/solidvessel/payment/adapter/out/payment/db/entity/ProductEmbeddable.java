package com.solidvessel.payment.adapter.out.payment.db.entity;

import com.solidvessel.payment.product.model.Product;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductEmbeddable {

    private Long productId;
    private int quantity;
    private String name;
    private Double price;

    public static ProductEmbeddable from(Product product) {
        return new ProductEmbeddable(product.getId(), product.getQuantity(), product.getName(), product.getPrice());
    }

    public Product toDomainModel() {
        return new Product(productId, quantity, name, price);
    }
}
