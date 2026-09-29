package com.solidvessel.inventory.product.event;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    ProductSubcategory productSubcategory, Integer desiredQuantity, String customerId) {

}
