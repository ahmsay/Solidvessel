package com.solidvessel.payment.cart.model;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@SuperBuilder
public class Cart extends DomainModel {

    private String customerId;
    private Map<Long, Product> products;

    public static Cart newCart(String customerId) {
        return new Cart(customerId, new HashMap<>());
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

    public List<Product> getProductList() {
        return products.values().stream().toList();
    }

    public Double getTotalPrice() {
        return products.values().stream().map(Product::getTotalPrice).reduce(0D, Double::sum);
    }

    public Map<Long, Integer> getProductQuantities() {
        return products.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getQuantity()));
    }
}
