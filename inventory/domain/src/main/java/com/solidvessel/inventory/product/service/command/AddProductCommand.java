package com.solidvessel.inventory.product.service.command;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductAttribute;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;

import java.util.List;

public record AddProductCommand(String name, String description, String brand, String manufacturer, Double price,
                                ProductCategory category,
                                ProductSubcategory subcategory, Integer quantity, List<ProductAttribute> attributes) {

    public Product toDomainModel() {
        return Product.newProduct(name, description, brand, manufacturer, price, category, subcategory, quantity, attributes);
    }
}
