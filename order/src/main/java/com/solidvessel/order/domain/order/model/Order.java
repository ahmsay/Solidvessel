package com.solidvessel.order.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private OrderStatus status;
    private Long customerId;
    private Long paymentId;

    public static Order newOrder(Long customerId, Long paymentId) {
        return new Order(null, OrderStatus.PREPARING, customerId, paymentId);
    }
}
