package com.solidvessel.payment.adapter.out.cart.db.entity;

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
import java.util.stream.Collectors;

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
        return Cart.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .customerId(customerId)
                .products(products.stream().collect(Collectors.toMap(ProductEmbeddable::getProductId, ProductEmbeddable::toDomainModel)))
                .build();
    }

    public static CartJpaEntity from(Cart cart) {
        return CartJpaEntity.builder()
                .id(cart.getId())
                .createdDate(cart.getCreatedDate())
                .lastModifiedDate(cart.getLastModifiedDate())
                .version(cart.getVersion())
                .customerId(cart.getCustomerId())
                .products(cart.getProductList().stream().map(ProductEmbeddable::from).toList())
                .build();
    }
}
