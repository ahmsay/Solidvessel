package com.solidvessel.order.adapter.in.order.rest.request;

import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.service.command.CancelOrderCommand;
import com.solidvessel.shared.security.SessionUtil;

public record CancelOrderRequest(CancellationReason cancellationReason, String explanation) {

    public CancelOrderCommand toCommand(Long id) {
        return new CancelOrderCommand(id, SessionUtil.getCurrentUserId(), cancellationReason, explanation);
    }
}
