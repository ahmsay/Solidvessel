package com.solidvessel.order.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private OrderStatus status;
    private String customerId;
    private Long paymentId;

    public static Order newOrder(String customerId, Long paymentId) {
        return new Order(null, OrderStatus.PREPARING, customerId, paymentId);
    }
}
