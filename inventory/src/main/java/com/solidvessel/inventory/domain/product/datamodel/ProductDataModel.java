package com.solidvessel.inventory.domain.product.datamodel;

import com.solidvessel.inventory.domain.product.model.ProductCategory;

public record ProductDataModel(Long id, String name, Double price, ProductCategory category) {
}
