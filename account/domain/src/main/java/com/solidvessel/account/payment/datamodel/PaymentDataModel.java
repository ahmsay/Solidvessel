package com.solidvessel.account.payment.datamodel;

import com.solidvessel.account.payment.model.Payment;

public record PaymentDataModel(Long id, Double totalCharge) {

    public Payment toDomainModel() {
        return new Payment(id, totalCharge);
    }

    public static PaymentDataModel from(Payment payment) {
        return new PaymentDataModel(payment.getId(), payment.getTotalCharge());
    }
}
