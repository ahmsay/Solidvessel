package com.solidvessel.inventory.product.model;

import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
public class Product extends DomainModel {

    private String name;
    private Double price;
    private ProductCategory category;
    private int quantity;
    private Boolean isAvailable;

    public static Product newProduct(String name, Double price, ProductCategory category, int quantity) {
        return new Product(name, price, category, quantity, true);
    }

    public void decreaseQuantity(int boughtQuantity) {
        quantity = quantity - boughtQuantity;
    }

    public boolean isInStock(int desiredQuantity) {
        return quantity >= desiredQuantity;
    }
}
