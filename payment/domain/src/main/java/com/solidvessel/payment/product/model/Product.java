package com.solidvessel.payment.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private int quantity;
    private String name;
    private Double price;
}
