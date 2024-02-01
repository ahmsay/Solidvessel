package com.solidvessel.order.adapter.out.order.db.entity;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.shared.persistence.BaseEntity;
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

    public Order toDomainModel() {
        return Order.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .status(status)
                .customerId(customerId)
                .paymentId(paymentId)
                .build();
    }

    public static OrderJpaEntity from(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId())
                .createdDate(order.getCreatedDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .version(order.getVersion())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .paymentId(order.getPaymentId())
                .build();
    }
}
