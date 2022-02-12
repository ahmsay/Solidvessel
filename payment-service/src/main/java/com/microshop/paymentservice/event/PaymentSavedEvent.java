package com.microshop.paymentservice.event;

public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
