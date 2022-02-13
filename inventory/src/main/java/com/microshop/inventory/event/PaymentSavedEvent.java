package com.microshop.inventory.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentSavedEvent(Long paymentId, List<Long> productIds) {
}
