package com.microshop.payment.response;

import com.microshop.payment.entity.Payment;

public record PaymentResponse(Long id, Double totalCharge, Long customerId) {

    public static PaymentResponse from(final Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getTotalCharge(), payment.getCustomerId());
    }
}