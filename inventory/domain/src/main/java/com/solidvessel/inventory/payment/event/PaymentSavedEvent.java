package com.solidvessel.inventory.payment.event;

import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;

import java.util.Map;

public record PaymentSavedEvent(Long paymentId, String customerId, Map<Long, Integer> productQuantities) {

    public UpdateProductQuantitiesCommand toCommand() {
        return new UpdateProductQuantitiesCommand(paymentId, productQuantities);
    }
}
