package com.solidvessel.order.adapter.out.payment.rest.response;

import com.solidvessel.order.payment.model.Payment;

public record PaymentResponse(Long id, Double totalCharge) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getTotalCharge());
    }

    public Payment toDomainModel() {
        return new Payment(id, totalCharge);
    }
}
