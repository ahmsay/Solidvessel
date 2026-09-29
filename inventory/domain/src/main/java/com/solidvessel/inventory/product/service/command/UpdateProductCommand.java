package com.solidvessel.inventory.product.service.command;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

public record UpdateProductCommand(Long id, String name, Double price, ProductCategory category,
                                   ProductSubcategory subcategory, Integer quantity) {

    public UpdateProductCommand(Long id, String name, Double price, ProductCategory category, Integer quantity) {
        this(id, name, price, category, null, quantity);
    }
}
