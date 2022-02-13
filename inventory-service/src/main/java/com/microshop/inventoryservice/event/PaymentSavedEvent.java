package com.microshop.inventoryservice.event;

import java.util.List;

public record PaymentSavedEvent(Long paymentId, List<Long> productIds) {
}
