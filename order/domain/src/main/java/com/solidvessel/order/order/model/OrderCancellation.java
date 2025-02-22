package com.solidvessel.order.order.model;

import java.io.Serializable;

public record OrderCancellation(CancellationReason cancellationReason, String explanation) implements Serializable {
}
