package com.solidvessel.payment.adapter.out.payment.db.entity;

import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
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
@Table(name = "payment")
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "payment_product", joinColumns = @JoinColumn(name = "payment_id"))
    private List<ProductEmbeddable> products = new ArrayList<>();

    @NotNull
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment toDomainModel() {
        return new Payment(
                id,
                customerId,
                products.stream().map(ProductEmbeddable::toDomainModel).toList(),
                totalPrice,
                status
        );
    }

    public static PaymentJpaEntity from(Payment payment) {
        return new PaymentJpaEntity(
                payment.getId(),
                payment.getCustomerId(),
                payment.getProducts().stream().map(ProductEmbeddable::from).toList(),
                payment.getTotalPrice(),
                payment.getStatus()
        );
    }
}
