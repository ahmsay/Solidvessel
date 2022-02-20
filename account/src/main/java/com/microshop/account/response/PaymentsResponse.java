package com.microshop.account.response;

import java.util.List;

public record PaymentsResponse(List<PaymentResponse> payments, String error) {

    public static PaymentsResponse from(List<PaymentResponse> payments) {
        return new PaymentsResponse(payments, "");
    }
}
