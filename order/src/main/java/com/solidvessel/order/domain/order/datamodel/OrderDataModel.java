package com.solidvessel.order.domain.order.datamodel;

import com.solidvessel.order.domain.order.model.OrderStatus;

public record OrderDataModel(Long id, OrderStatus status, Long customerId, Long paymentId) {
}
