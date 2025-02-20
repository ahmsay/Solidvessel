package com.solidvessel.order.order.service.command;

import com.solidvessel.order.order.model.CancellationReason;

public record CancelOrderCommand(Long id, String customerId, CancellationReason cancellationReason,
                                 String explanation) {
}
