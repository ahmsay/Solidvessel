package com.solidvessel.inventory.domain.product.service.command;

import java.util.Map;

public record UpdateProductQuantitiesCommand(Map<Long, Integer> productQuantities) {
}
