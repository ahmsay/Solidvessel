package com.microshop.account.response;

public record CustomerDetailResponse(Long id, String name, PaymentsResponse paymentsResponse,
                                     OrdersResponse ordersResponse) {
}
