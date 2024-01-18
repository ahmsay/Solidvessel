package com.solidvessel.payment.adapter.out.cart.db.entity;

import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.cart.model.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    private List<ProductEmbeddable> products = new ArrayList<>();

    public static CartJpaEntity from(Cart cart) {
        return new CartJpaEntity(
                cart.getId(),
                cart.getCustomerId(),
                cart.getProductList().stream().map(ProductEmbeddable::from).toList()
        );
    }

    public Cart toDomainModel() {
        return new Cart(
                id,
                customerId,
                products.stream().collect(Collectors.toMap(ProductEmbeddable::getProductId, ProductEmbeddable::toDomainModel))
        );
    }
}
