package com.solidvessel.inventory.domain.payment.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solidvessel.inventory.domain.product.service.command.UpdateProductQuantitiesCommand;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentSavedEvent(Map<Long, Integer> productQuantities) {

    public UpdateProductQuantitiesCommand toCommand() {
        return new UpdateProductQuantitiesCommand(productQuantities);
    }
}
