package com.solidvessel.payment.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private ProductCategory category;
    private int quantity;

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }
}
