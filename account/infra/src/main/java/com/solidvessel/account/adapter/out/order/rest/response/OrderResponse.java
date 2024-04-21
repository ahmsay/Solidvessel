package com.solidvessel.account.adapter.out.order.rest.response;

import com.solidvessel.account.order.model.OrderStatus;

import java.io.Serializable;

public record OrderResponse(Long id, OrderStatus status, Long paymentId) implements Serializable {
}
