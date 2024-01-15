package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.product.event.ProductAvailableEvent;
import com.solidvessel.inventory.product.model.Product;
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
public class AddProductToCartCommandService implements CommandService<AddProductToCartCommand> {

    private final ProductQueryPort productQueryPort;
    private final EventPublisher<ProductAvailableEvent> productAvailableEventEventPublisher;

    @Override
    public OperationResult execute(AddProductToCartCommand command) {
        Product product = productQueryPort.getById(command.id());
        if (!product.isAvailable(command.quantity())) {
            throw new InventoryDomainException("The product is not available in stocks with your desired quantity.");
        }
        productAvailableEventEventPublisher.publish(ProductAvailableEvent.from(product, command.quantity(), command.customerId()));
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }
}
