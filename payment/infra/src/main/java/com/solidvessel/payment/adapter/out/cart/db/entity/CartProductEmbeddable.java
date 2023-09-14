package com.solidvessel.payment.adapter.out.cart.db.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductEmbeddable {

    private Long productId;
    private int quantity;
}
