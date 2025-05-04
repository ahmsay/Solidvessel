package com.solidvessel.order.adapter.in.order.rest.request;

import com.solidvessel.order.order.service.command.DeliverOrderCommand;

public record DeliverOrderRequest(String recipient) {

    public DeliverOrderCommand toCommand(Long id) {
        return new DeliverOrderCommand(id, recipient);
    }
}
