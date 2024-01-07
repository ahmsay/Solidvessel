package com.solidvessel.payment.adapter.out.product.rest.response;

import com.solidvessel.payment.product.model.Product;

public record ProductResponse(Long id, int quantity, String name, Double price) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getQuantity(), product.getName(), product.getPrice());
    }

    public Product toDomainModel() {
        return new Product(id, quantity, name, price);
    }
}
