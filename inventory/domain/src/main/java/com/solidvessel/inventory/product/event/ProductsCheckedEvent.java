package com.solidvessel.inventory.product.event;

public record ProductsCheckedEvent(Long paymentId, Boolean areProductsAvailable, String customerId) {
}
