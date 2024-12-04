package com.solidvessel.inventory.product.event;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    Integer desiredQuantity, String customerId) {

    public static ProductAvailableEvent from(Product product, Integer desiredQuantity, String customerId) {
        return new ProductAvailableEvent(product.getId(), product.getName(), product.getPrice(), product.getCategory(), desiredQuantity, customerId);
    }
}
