package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

public record ProductResponse(Long id, String name, String description, Double price, ProductCategory category,
                              ProductSubcategory subcategory, Integer quantity) {

}
