package com.microshop.accountservice.response;

public record OrderResponse(Long id, String status, Long paymentId) {
}
