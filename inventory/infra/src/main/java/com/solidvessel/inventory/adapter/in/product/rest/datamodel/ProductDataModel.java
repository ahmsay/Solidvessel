package com.solidvessel.inventory.adapter.in.product.rest.datamodel;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductDataModel(Long id, String name, Double price, ProductCategory category, int quantity) {

    public static ProductDataModel from(Product product) {
        return new ProductDataModel(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getQuantity()
        );
    }
}
