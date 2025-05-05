package com.solidvessel.order.adapter.out.order.db.entity;

import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderCancellation;
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

import java.util.Optional;

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

    public Order toDomainModel() {
        return Order.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .status(status)
                .customerId(customerId)
                .paymentId(paymentId)
                .address(address)
                .cancellation(new OrderCancellation(cancellationReason, cancellationExplanation))
                .recipient(recipient)
                .build();
    }

    public static OrderJpaEntity from(Order order) {
        var cancellation = Optional.ofNullable(order.getCancellation());
        CancellationReason reason = cancellation.map(OrderCancellation::cancellationReason).orElse(null);
        String explanation = cancellation.map(OrderCancellation::explanation).orElse(null);
        return OrderJpaEntity.builder()
                .id(order.getId())
                .createdDate(order.getCreatedDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .version(order.getVersion())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .paymentId(order.getPaymentId())
                .address(order.getAddress())
                .cancellationReason(reason)
                .cancellationExplanation(explanation)
                .recipient(order.getRecipient())
                .build();
    }
}
