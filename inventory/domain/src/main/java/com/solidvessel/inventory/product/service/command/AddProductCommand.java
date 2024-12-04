package com.solidvessel.inventory.product.service.command;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;

public record AddProductCommand(String name, Double price, ProductCategory category, Integer quantity) {

    public Product toDomainModel() {
        return Product.newProduct(name, price, category, quantity);
    }
}
