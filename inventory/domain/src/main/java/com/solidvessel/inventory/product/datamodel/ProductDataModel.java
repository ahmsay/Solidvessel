package com.solidvessel.inventory.product.datamodel;

import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductDataModel(Long id, String name, Double price, ProductCategory category, int quantity) {
}
