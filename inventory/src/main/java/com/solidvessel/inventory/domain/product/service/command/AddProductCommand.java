package com.solidvessel.inventory.domain.product.service.command;

import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.model.ProductCategory;

public record AddProductCommand(String name, Double price, ProductCategory category) {

    public Product toDomainModel() {
        return Product.newProduct(name, price, category);
    }
}
