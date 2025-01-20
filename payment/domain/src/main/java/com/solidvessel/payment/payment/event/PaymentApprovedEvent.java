package com.solidvessel.payment.payment.event;

public record PaymentApprovedEvent(Long paymentId, String customerId, String address) {
}
