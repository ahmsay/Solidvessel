package com.microshop.accountservice.response;

import java.util.List;

public record CustomerDetailResponse(Long id, String name, List<PaymentResponse> payments, List<OrderResponse> orders) {
}
