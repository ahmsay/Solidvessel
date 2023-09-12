package com.solidvessel.order.order.datamodel;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.payment.datamodel.PaymentDataModel;

public record OrderDetailDataModel(Long id, OrderStatus status, CustomerDataModel customer, PaymentDataModel payment) {

    public static OrderDetailDataModel from(OrderDataModel order, CustomerDataModel customer, PaymentDataModel payment) {
        return new OrderDetailDataModel(
                order.id(),
                order.status(),
                customer,
                payment
        );
    }
}
