package com.solidvessel.order.adapter.in.order.rest.request;

import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.service.command.CancelOrderCommand;

public record CancelOrderRequest(CancellationReason reason, String explanation) {

    public CancelOrderCommand toCommand(Long id) {
        return new CancelOrderCommand(id, reason, explanation);
    }
}
