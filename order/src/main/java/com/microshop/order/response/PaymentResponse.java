package com.microshop.order.response;

public record PaymentResponse(Long id, Double totalCharge, String error) {
}
