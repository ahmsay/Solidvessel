package com.solidvessel.order.payment.event;

import com.solidvessel.order.order.service.command.AddOrderCommand;

import java.util.Map;

public record PaymentSavedEvent(Long paymentId, String customerId, Map<Long, Integer> productQuantities) {

    public AddOrderCommand toCommand() {
        return new AddOrderCommand(customerId, paymentId);
    }
}
