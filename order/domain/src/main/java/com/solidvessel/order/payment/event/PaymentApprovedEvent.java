package com.solidvessel.order.payment.event;

public record PaymentApprovedEvent(Long paymentId, String customerId, String address) {
}
