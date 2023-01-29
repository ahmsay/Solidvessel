package com.solidvessel.order.response;

import com.solidvessel.order.entity.CustomerOrder;

public record OrderResponse(Long id, String status, Long customerId, Long paymentId) {

    public static OrderResponse from(final CustomerOrder order) {
        return new OrderResponse(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
