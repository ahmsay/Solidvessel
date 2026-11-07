package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

import java.util.List;

public record ProductResponse(Long id, String name, String description, String brand, String manufacturer, Double price,
                              ProductCategory category,
                              ProductSubcategory subcategory, Integer quantity, List<ProductAttributeResponse> attributes) {

}
