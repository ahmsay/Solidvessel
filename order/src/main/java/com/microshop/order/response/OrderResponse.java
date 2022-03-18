package com.microshop.order.response;

import com.microshop.order.entity.CustomerOrder;

public record OrderResponse(Long id, String status, Long customerId, Long paymentId) {

    public static OrderResponse from(final CustomerOrder order) {
        return new OrderResponse(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
