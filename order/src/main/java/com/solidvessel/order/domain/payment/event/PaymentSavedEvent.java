package com.solidvessel.order.domain.payment.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentSavedEvent(Long paymentId, Long customerId) {
}
