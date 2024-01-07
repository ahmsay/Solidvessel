package com.solidvessel.order.order.datamodel;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.customer.model.Customer;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.payment.model.Payment;

public record OrderDetailDataModel(Long id, OrderStatus status, CustomerDataModel customer, PaymentDataModel payment) {

    public static OrderDetailDataModel from(Order order, Customer customer, Payment payment) {
        return new OrderDetailDataModel(
                order.getId(),
                order.getStatus(),
                CustomerDataModel.from(customer),
                PaymentDataModel.from(payment)
        );
    }
}
