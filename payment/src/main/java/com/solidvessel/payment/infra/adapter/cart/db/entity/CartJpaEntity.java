package com.solidvessel.payment.infra.adapter.cart.db.entity;

import com.solidvessel.payment.domain.cart.model.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
                cart.getProducts().stream().map(CartProductEmbeddable::from).toList()
        );
    }
}
