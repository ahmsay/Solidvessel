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

    public static Cart newCart(Long customerId) {
        return new Cart(null, customerId, new HashMap<>());
    }

    public void addProduct(Long productId, int quantity) {
        if (products.containsKey(productId)) {
            products.put(productId, products.get(productId) + quantity);
        } else {
            products.put(productId, quantity);
        }
    }

    public boolean doesProductExist(Long productId) {
        return products.containsKey(productId);
    }

    public void removeProduct(Long productId) {
        if (products.get(productId) == 1) {
            products.remove(productId);
        } else {
            products.put(productId, products.get(productId) - 1);
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void empty() {
        products = new HashMap<>();
    }
}
