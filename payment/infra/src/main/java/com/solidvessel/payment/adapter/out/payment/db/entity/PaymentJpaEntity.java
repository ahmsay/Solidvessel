package com.solidvessel.payment.adapter.out.payment.db.entity;

import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
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
@Table(name = "payment")
public class PaymentJpaEntity extends BaseEntity {

    @NotNull
    private String customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "payment_product", joinColumns = @JoinColumn(name = "payment_id"))
    private List<ProductEmbeddable> products;

    @NotNull
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment toDomainModel() {
        return Payment.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .customerId(customerId)
                .products(products.stream().map(ProductEmbeddable::toDomainModel).toList())
                .totalPrice(totalPrice)
                .status(status)
                .build();
    }

    public static PaymentJpaEntity from(Payment payment) {
        return PaymentJpaEntity.builder()
                .id(payment.getId())
                .createdDate(payment.getCreatedDate())
                .lastModifiedDate(payment.getLastModifiedDate())
                .version(payment.getVersion())
                .customerId(payment.getCustomerId())
                .products(payment.getProducts().stream().map(ProductEmbeddable::from).toList())
                .totalPrice(payment.getTotalPrice())
                .status(payment.getStatus())
                .build();
    }
}
