package com.solidvessel.payment.adapter.in.payment.rest.response;

import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.payment.payment.model.Payment;

public record PaymentDetailResponse(PaymentResponse payment, CustomerResponse customer) {

    public static PaymentDetailResponse from(Payment payment, CustomerResponse customer) {
        return new PaymentDetailResponse(
                PaymentResponse.from(payment),
                customer
        );
    }
}
