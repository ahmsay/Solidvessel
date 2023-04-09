package com.solidvessel.payment.infra.adapter.cart.db.entity;

import com.solidvessel.payment.domain.product.model.CartProduct;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductEmbeddable {

    Long id;
    int quantity;

    public static CartProductEmbeddable from(CartProduct cartProduct) {
        return new CartProductEmbeddable(cartProduct.getId(), cartProduct.getQuantity());
    }
}
