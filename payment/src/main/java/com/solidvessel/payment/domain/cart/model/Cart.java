package com.solidvessel.payment.domain.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Cart {

    private Long id;
    private Long customerId;
    private Map<Long, Integer> products;

    public void addProduct(Long productId) {
        if (products.containsKey(productId)) {
            products.put(productId, products.get(productId) + 1);
        } else {
            products.put(productId, 1);
        }
    }

    public static Cart newCart(Long customerId) {
        return new Cart(null, customerId, new HashMap<>());
    }
}
