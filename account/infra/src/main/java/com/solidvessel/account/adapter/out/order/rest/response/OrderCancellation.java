package com.solidvessel.account.adapter.out.order.rest.response;

import java.io.Serializable;

public record OrderCancellation(CancellationReason cancellationReason, String explanation) implements Serializable {
}
