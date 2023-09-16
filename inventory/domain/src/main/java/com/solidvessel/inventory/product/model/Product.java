package com.solidvessel.inventory.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private String name;
    private Double price;
    private ProductCategory category;
    private int quantity;

    public static Product newProduct(String name, Double price, ProductCategory category, int quantity) {
        return new Product(null, name, price, category, quantity);
    }

    public void decreaseQuantity(int boughQuantity) {
        quantity = quantity - boughQuantity;
    }
}
