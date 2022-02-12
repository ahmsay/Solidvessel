package com.microshop.orderservice.response;

public record OrderDetailResponse(Long id, String status, CustomerResponse customer, PaymentResponse payment) {
}
