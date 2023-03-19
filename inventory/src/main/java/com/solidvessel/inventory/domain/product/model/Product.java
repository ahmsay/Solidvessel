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
    private Long paymentId;

    public Product(String name, Double price, String category, Long paymentId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.paymentId = paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
