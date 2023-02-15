package com.solidvessel.order.domain.order.datamodel;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;

public record OrderDetailDataModel(Long id, String status, CustomerDataModel customer, PaymentDataModel payment) {

    public static OrderDetailDataModel from(OrderDataModel order, CustomerDataModel customer, PaymentDataModel payment) {
        return new OrderDetailDataModel(
                order.id(),
                order.status(),
                customer,
                payment
        );
    }
}
