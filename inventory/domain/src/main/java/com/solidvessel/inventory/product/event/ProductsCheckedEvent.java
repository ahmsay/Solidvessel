package com.solidvessel.inventory.product.event;

public record ProductsCheckedEvent(Long paymentId, boolean areProductsAvailable, String customerId) {
}
