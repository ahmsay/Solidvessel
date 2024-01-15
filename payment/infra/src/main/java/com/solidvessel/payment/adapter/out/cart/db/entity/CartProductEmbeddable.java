package com.solidvessel.payment.adapter.out.cart.db.entity;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductEmbeddable {

    private Long productId;
    private String name;
    private Double price;
    private ProductCategory category;
    private int quantity;

    public static CartProductEmbeddable from(Product product) {
        return new CartProductEmbeddable(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getQuantity());
    }

    public Product toDomainModel() {
        return new Product(productId, name, price, category, quantity);
    }
}
