package com.solidvessel.account.order.datamodel;

import com.solidvessel.account.order.model.Order;

public record OrderDataModel(Long id, String status, Long paymentId) {

    public Order toDomainModel() {
        return new Order(id, status, paymentId);
    }

    public static OrderDataModel from(Order order) {
        return new OrderDataModel(order.getId(), order.getStatus(), order.getPaymentId());
    }
}
