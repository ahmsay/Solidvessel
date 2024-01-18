package com.solidvessel.payment.payment.service;

public record UpdatePaymentStatusCommand(Long paymentId, boolean areProductsAvailable, String customerId) {
}
