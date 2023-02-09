package com.solidvessel.account.infra.adapter.payment.rest.response;

import java.util.List;

public record PaymentsResponse(List<PaymentResponse> payments, String error) {

    public static PaymentsResponse from(List<PaymentResponse> payments) {
        return new PaymentsResponse(payments, "");
    }
}
