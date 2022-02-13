package com.microshop.order.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
