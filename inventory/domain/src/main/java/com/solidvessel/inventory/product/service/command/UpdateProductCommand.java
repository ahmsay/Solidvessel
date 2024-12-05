package com.solidvessel.inventory.product.service.command;

import com.solidvessel.inventory.product.model.ProductCategory;

public record UpdateProductCommand(Long id, String name, Double price, ProductCategory category, Integer quantity) {
}
