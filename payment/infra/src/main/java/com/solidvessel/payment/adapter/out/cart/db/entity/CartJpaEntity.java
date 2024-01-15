package com.solidvessel.payment.adapter.out.cart.db.entity;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
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
    private String customerId;

    @ElementCollection
    @CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartProductEmbeddable> products = new ArrayList<>();

    public static CartJpaEntity from(Cart cart) {
        return new CartJpaEntity(
                cart.getId(),
                cart.getCustomerId(),
                productMapToList(cart.getProducts())
        );
    }

    public Cart toDomainModel() {
        return new Cart(id, customerId, productListToMap(products));
    }

    private static List<CartProductEmbeddable> productMapToList(Map<Long, Product> products) {
        List<CartProductEmbeddable> productList = new ArrayList<>();
        products.values().forEach(product -> productList.add(CartProductEmbeddable.from(product)));
        return productList;
    }

    private Map<Long, Product> productListToMap(List<CartProductEmbeddable> products) {
        Map<Long, Product> productsMap = new HashMap<>();
        products.forEach(product -> productsMap.put(product.getProductId(), product.toDomainModel()));
        return productsMap;
    }
}
