package com.solidvessel.payment.domain.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    Long id;
    int quantity;
    Double discount;
    Double price;
}
