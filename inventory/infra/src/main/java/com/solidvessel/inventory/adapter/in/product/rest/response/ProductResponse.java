package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductResponse(Long id, String name, Double price, ProductCategory category, Integer quantity) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getQuantity()
        );
    }
}
