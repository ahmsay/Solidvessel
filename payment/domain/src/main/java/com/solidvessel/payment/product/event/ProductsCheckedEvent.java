package com.solidvessel.payment.product.event;

public record ProductsCheckedEvent(Long paymentId, boolean areProductsAvailable, String customerId) {
}
