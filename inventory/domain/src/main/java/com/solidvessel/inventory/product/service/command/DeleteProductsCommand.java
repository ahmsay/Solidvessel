package com.solidvessel.inventory.product.service.command;

import java.util.List;

public record DeleteProductsCommand(List<Long> productIds) {
}
