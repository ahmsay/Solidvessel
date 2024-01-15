package com.solidvessel.payment.cart.model;

import com.solidvessel.payment.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Cart {

    private Long id;
    private String customerId;
    private Map<Long, Product> products;

    public static Cart newCart(String customerId) {
        return new Cart(null, customerId, new HashMap<>());
    }

    public void addProduct(Product product) {
        Long id = product.getId();
        if (doesProductExist(id)) {
            products.get(id).increaseQuantity(product.getQuantity());
        } else {
            products.put(id, product);
        }
    }

    public boolean doesProductExist(Long productId) {
        return products.containsKey(productId);
    }

    public void removeProduct(Long productId) {
        if (products.get(productId).getQuantity() == 1) {
            products.remove(productId);
        } else {
            products.get(productId).decreaseQuantity();
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void empty() {
        products = new HashMap<>();
    }

    public Set<Long> getProductIds() {
        return products.keySet();
    }
}
