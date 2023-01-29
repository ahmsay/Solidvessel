package com.solidvessel.account.response;

public record CustomerDetailResponse(Long id, String firstName, String lastName, PaymentsResponse paymentsResponse,
                                     OrdersResponse ordersResponse) {
}
