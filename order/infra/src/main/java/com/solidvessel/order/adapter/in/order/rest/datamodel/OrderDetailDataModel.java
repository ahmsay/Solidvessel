package com.solidvessel.order.adapter.in.order.rest.datamodel;

import com.solidvessel.order.adapter.out.customer.rest.datamodel.CustomerDataModel;
import com.solidvessel.order.adapter.out.payment.rest.datamodel.PaymentDataModel;
import com.solidvessel.order.customer.model.Customer;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
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
