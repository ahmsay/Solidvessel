package com.solidvessel.order.adapter.out.payment.rest.datamodel;

import com.solidvessel.order.payment.model.Payment;

public record PaymentDataModel(Long id, Double totalCharge) {

    public static PaymentDataModel from(Payment payment) {
        return new PaymentDataModel(payment.getId(), payment.getTotalCharge());
    }

    public Payment toDomainModel() {
        return new Payment(id, totalCharge);
    }
}
