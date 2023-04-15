package com.solidvessel.inventory.domain.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private String name;
    private Double price;
    private String category;

    public static Product newProduct(String name, Double price, String category) {
        return new Product(null, name, price, category);
    }
}
