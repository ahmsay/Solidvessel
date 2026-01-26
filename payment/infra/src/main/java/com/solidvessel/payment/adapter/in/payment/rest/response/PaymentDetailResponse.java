package com.solidvessel.payment.adapter.in.payment.rest.response;

import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;

public record PaymentDetailResponse(PaymentResponse payment, CustomerResponse customer) {
}
