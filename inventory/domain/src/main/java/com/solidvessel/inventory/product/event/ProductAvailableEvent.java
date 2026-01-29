package com.solidvessel.inventory.product.event;

import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    Integer desiredQuantity, String customerId) {
}
