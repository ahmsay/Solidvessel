package com.solidvessel.account.adapter.out.order.rest.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OrderResponse(Long id, OrderStatus status, Long paymentId, String address,
                            LocalDateTime creationDate, OrderCancellation cancellation,
                            String recipient) implements Serializable {
}
