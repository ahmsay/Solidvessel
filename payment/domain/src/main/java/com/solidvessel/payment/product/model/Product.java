package com.solidvessel.payment.product.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private ProductCategory category;
    private Integer quantity;

    public void increaseQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
