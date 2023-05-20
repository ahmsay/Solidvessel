package com.solidvessel.order.domain.payment.event;

import com.solidvessel.order.domain.order.service.command.AddOrderCommand;

public record PaymentSavedEvent(Long paymentId, Long customerId) {

    public AddOrderCommand toCommand() {
        return new AddOrderCommand(customerId, paymentId);
    }
}
