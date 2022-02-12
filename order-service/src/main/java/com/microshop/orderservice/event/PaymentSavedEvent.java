package com.microshop.orderservice.event;

public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
