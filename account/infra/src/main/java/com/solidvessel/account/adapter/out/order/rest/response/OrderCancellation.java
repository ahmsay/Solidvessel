package com.solidvessel.account.adapter.out.order.rest.response;

public record OrderCancellation(CancellationReason cancellationReason, String explanation) {
}
