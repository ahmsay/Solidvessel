package com.solidvessel.account.infra.adapter.order.rest.response;

public record OrderResponse(Long id, String status, Long paymentId) {
}
