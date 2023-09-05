package com.solidvessel.inventory.domain.payment.event;

import com.solidvessel.inventory.domain.product.service.command.UpdateProductQuantitiesCommand;

import java.util.Map;

public record PaymentSavedEvent(Map<Long, Integer> productQuantities) {

    public UpdateProductQuantitiesCommand toCommand() {
        return new UpdateProductQuantitiesCommand(productQuantities);
    }
}
