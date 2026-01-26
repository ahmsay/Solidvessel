package com.solidvessel.order.adapter.in.order.rest.response;

import com.solidvessel.order.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.order.model.OrderCancellation;
import com.solidvessel.order.order.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderDetailResponse(Long id, OrderStatus status, String address, LocalDateTime createdDate,
                                  OrderCancellation cancellation, String recipient, CustomerResponse customer,
                                  PaymentResponse payment) {
}
