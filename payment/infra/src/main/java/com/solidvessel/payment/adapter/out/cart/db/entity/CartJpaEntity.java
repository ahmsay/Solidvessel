package com.solidvessel.payment.adapter.out.cart.db.entity;

import com.solidvessel.payment.cart.model.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "cart")
public class CartJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId;

    @ElementCollection
    @CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartProductEmbeddable> products = new ArrayList<>();

    public static CartJpaEntity from(Cart cart) {
        return new CartJpaEntity(
                cart.getId(),
                cart.getCustomerId(),
                productMapToList(cart.getProductQuantities())
        );
    }

    public Cart toDomainModel() {
        return new Cart(id, customerId, productListToMap(products));
    }

    private static List<CartProductEmbeddable> productMapToList(Map<Long, Integer> products) {
        return products.entrySet().stream()
                .map(entry -> new CartProductEmbeddable(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<Long, Integer> productListToMap(List<CartProductEmbeddable> products) {
        Map<Long, Integer> productsMap = new HashMap<>();
        products.forEach(product -> productsMap.put(product.getProductId(), product.getQuantity()));
        return productsMap;
    }
}
