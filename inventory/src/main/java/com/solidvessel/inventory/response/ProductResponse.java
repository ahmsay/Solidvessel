package com.solidvessel.inventory.response;

import com.solidvessel.inventory.entity.Product;

public record ProductResponse(Long id, String name, Double price, String category, Long paymentId) {

    public static ProductResponse from(final Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getPaymentId());
    }
}
