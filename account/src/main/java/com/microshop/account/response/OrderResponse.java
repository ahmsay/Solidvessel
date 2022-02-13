package com.microshop.account.response;

public record OrderResponse(Long id, String status, Long paymentId) {
}
