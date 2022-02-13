package com.microshop.order.response;

public record OrderDetailResponse(Long id, String status, CustomerResponse customer, PaymentResponse payment) {
}
