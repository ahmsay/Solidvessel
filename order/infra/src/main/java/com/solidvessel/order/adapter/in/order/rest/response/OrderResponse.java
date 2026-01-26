package com.solidvessel.order.adapter.in.order.rest.response;

import com.solidvessel.order.order.model.OrderCancellation;
import com.solidvessel.order.order.model.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OrderResponse(Long id, OrderStatus status, String customerId, Long paymentId,
                            String address, LocalDateTime createdDate,
                            OrderCancellation cancellation, String recipient) implements Serializable {
}