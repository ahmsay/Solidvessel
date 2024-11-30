package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.product.event.ProductAvailableEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductAvailability;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.AddProductToCartCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddProductToCartCommandService implements CommandService<AddProductToCartCommand, OperationResult> {

    private final ProductQueryPort productQueryPort;
    private final EventPublisher<ProductAvailableEvent> productAvailableEventEventPublisher;

    @Override
    public OperationResult execute(AddProductToCartCommand command) {
        Product product = productQueryPort.getById(command.id());
        ProductAvailability availability = product.isAvailable(command.quantity());
        if (!availability.getIsAvailable()) {
            throw new InventoryDomainException("The product is not available. Reason: %s".formatted(availability.getUnavailableReason().toString()));
        }
        productAvailableEventEventPublisher.publish(ProductAvailableEvent.from(product, command.quantity(), command.customerId()));
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }
}
