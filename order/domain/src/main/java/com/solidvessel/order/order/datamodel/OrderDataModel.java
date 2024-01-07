package com.solidvessel.order.order.datamodel;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;

public record OrderDataModel(Long id, OrderStatus status, String customerId, Long paymentId) {

    public static OrderDataModel from(Order order) {
        return new OrderDataModel(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
