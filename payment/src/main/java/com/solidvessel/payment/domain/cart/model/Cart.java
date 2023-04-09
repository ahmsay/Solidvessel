package com.solidvessel.payment.domain.cart.model;

import com.solidvessel.payment.domain.product.model.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Cart {

    private Long id;
    private Long customerId;
    private List<CartProduct> products;

    public static Cart newCart(Long customerId) {
        return new Cart(null, customerId, new ArrayList<>());
    }
}
