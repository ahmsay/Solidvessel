package com.solidvessel.order.adapter.in.order.rest.response;

import com.solidvessel.order.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;

public record OrderDetailResponse(Long id, OrderStatus status, CustomerResponse customer, PaymentResponse payment) {

    public static OrderDetailResponse from(Order order, CustomerResponse customer, PaymentResponse payment) {
        return new OrderDetailResponse(
                order.getId(),
                order.getStatus(),
                customer,
                payment
        );
    }
}
