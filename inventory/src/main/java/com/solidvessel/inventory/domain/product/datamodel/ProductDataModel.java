package com.solidvessel.inventory.domain.product.datamodel;

import com.solidvessel.inventory.domain.product.model.Product;

public record ProductDataModel(Long id, String name, Double price, String category, Long paymentId) {

    public Product toDomainModel() {
        return new Product(id, name, price, category, paymentId);
    }
}
