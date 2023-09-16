package com.solidvessel.payment.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Cart {

    private Long id;
    private Long customerId;
    private Map<Long, Integer> productQuantities;

    public static Cart newCart(Long customerId) {
        return new Cart(null, customerId, new HashMap<>());
    }

    public void addProduct(Long productId, int quantity) {
        if (productQuantities.containsKey(productId)) {
            productQuantities.put(productId, productQuantities.get(productId) + quantity);
        } else {
            productQuantities.put(productId, quantity);
        }
    }

    public boolean doesProductExist(Long productId) {
        return productQuantities.containsKey(productId);
    }

    public void removeProduct(Long productId) {
        if (productQuantities.get(productId) == 1) {
            productQuantities.remove(productId);
        } else {
            productQuantities.put(productId, productQuantities.get(productId) - 1);
        }
    }

    public boolean isEmpty() {
        return productQuantities.isEmpty();
    }

    public void empty() {
        productQuantities = new HashMap<>();
    }

    public Set<Long> getProductIds() {
        return productQuantities.keySet();
    }
}
