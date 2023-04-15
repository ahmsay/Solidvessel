package com.solidvessel.payment.domain.payment.event;

public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
