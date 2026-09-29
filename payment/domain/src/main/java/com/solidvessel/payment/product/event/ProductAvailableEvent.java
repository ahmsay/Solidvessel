package com.solidvessel.payment.product.event;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.payment.product.model.ProductSubcategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    ProductSubcategory productSubcategory, Integer desiredQuantity, String customerId) {

    public ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                 Integer desiredQuantity, String customerId) {
        this(id, name, price, productCategory, null, desiredQuantity, customerId);
    }

    public Product toDomainModel() {
        return new Product(id, name, price, productCategory, productSubcategory, desiredQuantity);
    }
}
