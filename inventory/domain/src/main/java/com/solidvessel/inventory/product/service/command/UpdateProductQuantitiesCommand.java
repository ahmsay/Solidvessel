package com.solidvessel.inventory.product.service.command;

import java.util.Map;

public record UpdateProductQuantitiesCommand(Long paymentId, Map<Long, Integer> productQuantities) {
}
