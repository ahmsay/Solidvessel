package com.solidvessel.inventory.payment.event;

import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;

import java.util.Map;

public record PaymentSavedEvent(Long paymentId, Long customerId, Map<Long, Integer> productQuantities) {

    public UpdateProductQuantitiesCommand toCommand() {
        return new UpdateProductQuantitiesCommand(productQuantities);
    }
}
