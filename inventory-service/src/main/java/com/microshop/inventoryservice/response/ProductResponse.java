package com.microshop.inventoryservice.response;

import com.microshop.inventoryservice.entity.Product;

public record ProductResponse(Long id, String name, Double price, String category) {

    public static ProductResponse from(final Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }
}
