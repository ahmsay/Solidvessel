package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

public record ProductResponse(Long id, String name, Double price, ProductCategory category,
                              ProductSubcategory subcategory, Integer quantity) {

    public ProductResponse(Long id, String name, Double price, ProductCategory category, Integer quantity) {
        this(id, name, price, category, null, quantity);
    }
}
