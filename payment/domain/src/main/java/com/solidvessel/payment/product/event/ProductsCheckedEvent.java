package com.solidvessel.payment.product.event;

public record ProductsCheckedEvent(Long paymentId, Boolean areProductsAvailable, String customerId) {
}
