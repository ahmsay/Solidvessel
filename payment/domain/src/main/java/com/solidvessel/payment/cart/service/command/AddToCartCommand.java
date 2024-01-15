package com.solidvessel.payment.cart.service.command;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;

public record AddToCartCommand(Long productId, String name, Double price, ProductCategory category, int quantity,
                               String customerId) {

    public Product toDomainModel() {
        return new Product(productId, name, price, category, quantity);
    }
}
