package com.solidvessel.inventory.product.event;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    int desiredQuantity) {

    public static ProductAvailableEvent from(Product product, int desiredQuantity) {
        return new ProductAvailableEvent(product.getId(), product.getName(), product.getPrice(), product.getCategory(), desiredQuantity);
    }
}
