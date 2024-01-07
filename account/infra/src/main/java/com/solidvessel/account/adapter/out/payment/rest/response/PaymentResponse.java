package com.solidvessel.account.adapter.out.payment.rest.response;

import com.solidvessel.account.payment.model.Payment;

public record PaymentResponse(Long id, Double totalCharge) {

    public Payment toDomainModel() {
        return new Payment(id, totalCharge);
    }

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getTotalCharge());
    }
}
