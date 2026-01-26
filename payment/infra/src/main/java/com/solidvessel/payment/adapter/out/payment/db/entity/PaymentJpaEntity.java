package com.solidvessel.payment.adapter.out.payment.db.entity;

import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
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
}
