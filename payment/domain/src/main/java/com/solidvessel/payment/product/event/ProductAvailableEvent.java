package com.solidvessel.payment.product.event;

import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.product.model.ProductCategory;

public record ProductAvailableEvent(Long id, String name, Double price, ProductCategory productCategory,
                                    int desiredQuantity, String customerId) {

    public AddToCartCommand toCommand() {
        return new AddToCartCommand(id, name, price, productCategory, desiredQuantity, customerId);
    }
}
