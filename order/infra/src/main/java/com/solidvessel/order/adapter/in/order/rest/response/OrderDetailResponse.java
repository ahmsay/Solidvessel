package com.solidvessel.order.adapter.in.order.rest.response;

import com.solidvessel.order.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;

public record OrderDetailResponse(OrderResponse order, CustomerResponse customer,
                                  PaymentResponse payment) {
}
