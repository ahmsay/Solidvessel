package com.microshop.account.response;

import java.util.List;

public record CustomerDetailResponse(Long id, String name, List<PaymentResponse> payments, List<OrderResponse> orders) {
}
