package com.solidvessel.order.adapter.out.order.db.entity;

import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.shared.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "orders")
public class OrderJpaEntity extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private String customerId;

    @NotNull
    private Long paymentId;

    @NotNull
    private String address;

    private CancellationReason cancellationReason;

    private String cancellationExplanation;

    private String recipient;
}
