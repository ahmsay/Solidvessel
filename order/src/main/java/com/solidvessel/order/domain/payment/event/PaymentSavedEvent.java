package com.solidvessel.order.domain.payment.event;

public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
