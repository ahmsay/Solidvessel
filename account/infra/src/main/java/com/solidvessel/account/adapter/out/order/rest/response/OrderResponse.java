package com.solidvessel.account.adapter.out.order.rest.response;

import com.solidvessel.account.order.model.Order;

public record OrderResponse(Long id, String status, Long paymentId) {

    public Order toDomainModel() {
        return new Order(id, status, paymentId);
    }

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getId(), order.getStatus(), order.getPaymentId());
    }
}
