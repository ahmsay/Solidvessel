package com.solidvessel.inventory.product.event;

public record InventoryCheckedEvent(Long paymentId, boolean areProductsAvailable) {
}
