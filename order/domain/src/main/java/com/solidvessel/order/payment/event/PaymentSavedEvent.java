package com.solidvessel.order.payment.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solidvessel.order.order.service.command.AddOrderCommand;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentSavedEvent(Long paymentId, Long customerId) {

    public AddOrderCommand toCommand() {
        return new AddOrderCommand(customerId, paymentId);
    }
}
