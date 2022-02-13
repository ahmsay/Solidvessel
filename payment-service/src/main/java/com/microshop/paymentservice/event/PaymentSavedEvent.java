package com.microshop.paymentservice.event;

import java.util.List;

public record PaymentSavedEvent(Long paymentId, Long customerId, List<Long> productIds) {
}
