package com.solidvessel.payment.product.event;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    Integer desiredQuantity, String customerId) {

    public Product toDomainModel() {
        return new Product(id, name, price, productCategory, desiredQuantity);
    }
}
