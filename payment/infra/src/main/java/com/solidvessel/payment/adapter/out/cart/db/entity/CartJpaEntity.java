package com.solidvessel.payment.adapter.out.cart.db.entity;

import com.solidvessel.payment.adapter.out.cart.db.mapper.CartJpaMapper;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.shared.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "cart")
public class CartJpaEntity extends BaseEntity {

    @NotNull
    private String customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    private List<ProductEmbeddable> products;

    public Cart toDomainModel() {
        return CartJpaMapper.INSTANCE.toDomainModel(this);
    }

    public static CartJpaEntity from(Cart cart) {
        return CartJpaMapper.INSTANCE.toJpaEntity(cart);
    }
}
