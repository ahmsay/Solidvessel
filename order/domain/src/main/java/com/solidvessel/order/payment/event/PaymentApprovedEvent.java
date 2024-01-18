package com.solidvessel.order.payment.event;

import com.solidvessel.order.order.service.command.AddOrderCommand;

public record PaymentApprovedEvent(Long paymentId, String customerId) {

    public AddOrderCommand toCommand() {
        return new AddOrderCommand(customerId, paymentId);
    }
}
