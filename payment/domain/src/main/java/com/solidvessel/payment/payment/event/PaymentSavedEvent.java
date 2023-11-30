package com.solidvessel.payment.payment.event;

import java.util.Map;

public record PaymentSavedEvent(Long paymentId, String customerId, Map<Long, Integer> productQuantities) {
}
