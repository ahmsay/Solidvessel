package com.solidvessel.order.order.datamodel;

import com.solidvessel.order.order.model.OrderStatus;

public record OrderDataModel(Long id, OrderStatus status, Long customerId, Long paymentId) {
}
