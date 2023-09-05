package com.solidvessel.payment.domain.payment.event;

import java.util.Map;

public record PaymentSavedEvent(Long paymentId, Long customerId, Map<Long, Integer> productQuantities) {
}
