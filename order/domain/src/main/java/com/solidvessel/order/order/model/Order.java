package com.solidvessel.order.order.model;

import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Order extends DomainModel {

    private OrderStatus status;
    private String customerId;
    private Long paymentId;

    public static Order newOrder(String customerId, Long paymentId) {
        return Order.builder()
                .status(OrderStatus.PREPARING)
                .customerId(customerId)
                .paymentId(paymentId)
                .build();
    }
}
