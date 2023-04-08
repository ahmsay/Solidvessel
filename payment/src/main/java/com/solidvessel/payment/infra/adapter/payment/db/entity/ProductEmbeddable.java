package com.solidvessel.payment.infra.adapter.payment.db.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductEmbeddable {

    private Long productId;
    private Long paymentId;
    private int quantity;
    private Double price;


}

/*
payment
- id
- customer id
- total charge
- products (id, quantity)
cart
- id
- customer id
- products (id, quantity)
*/