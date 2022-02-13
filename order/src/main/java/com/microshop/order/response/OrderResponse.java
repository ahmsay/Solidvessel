package com.microshop.order.response;

import com.microshop.order.entity.Order;

public record OrderResponse(Long id, String status, Long customerId, Long paymentId) {

    public static OrderResponse from(final Order order) {
        return new OrderResponse(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
