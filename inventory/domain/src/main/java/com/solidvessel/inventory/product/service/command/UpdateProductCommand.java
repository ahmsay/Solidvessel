package com.solidvessel.inventory.product.service.command;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

public record UpdateProductCommand(Long id, String name, String description, String brand, String manufacturer, Double price,
                                   ProductCategory category,
                                   ProductSubcategory subcategory, Integer quantity) {

}
