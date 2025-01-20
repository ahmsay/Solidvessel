package com.solidvessel.account.adapter.out.order.rest.response;

import java.io.Serializable;

public record OrderResponse(Long id, OrderStatus status, Long paymentId, String address) implements Serializable {
}
