package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.ProductCategory;

public record ProductResponse(Long id, String name, Double price, ProductCategory category, Integer quantity) {
}
