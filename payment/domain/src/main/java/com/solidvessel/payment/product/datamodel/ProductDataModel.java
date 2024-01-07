package com.solidvessel.payment.product.datamodel;

import com.solidvessel.payment.product.model.Product;

public record ProductDataModel(Long id, int quantity, String name, Double price) {

    public static ProductDataModel from(Product product) {
        return new ProductDataModel(product.getId(), product.getQuantity(), product.getName(), product.getPrice());
    }

    public Product toDomainModel() {
        return new Product(id, quantity, name, price);
    }
}
