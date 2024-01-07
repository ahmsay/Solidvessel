package com.solidvessel.order.adapter.out.order.db.entity;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private String customerId;

    @NotNull
    private Long paymentId;

    public Order toDomainModel() {
        return new Order(id, status, customerId, paymentId);
    }

    public static OrderJpaEntity from(Order order) {
        return new OrderJpaEntity(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
