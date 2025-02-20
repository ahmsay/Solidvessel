package com.solidvessel.order.order.model;

public record OrderCancellation(CancellationReason cancellationReason, String explanation) {
}
