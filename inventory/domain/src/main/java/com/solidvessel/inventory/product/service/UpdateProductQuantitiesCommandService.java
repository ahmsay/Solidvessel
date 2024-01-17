package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.product.event.InventoryCheckedEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class UpdateProductQuantitiesCommandService implements CommandService<UpdateProductQuantitiesCommand> {

    private final ProductPort productPort;
    private final ProductQueryPort productQueryPort;
    private final EventPublisher<InventoryCheckedEvent> inventoryCheckedEventPublisher;

    @Override
    public OperationResult execute(UpdateProductQuantitiesCommand command) {
        var productQuantities = command.productQuantities();
        List<Product> products = productQueryPort.getByIds(productQuantities.keySet().stream().toList());
        products.forEach(product -> {
            int boughtQuantity = productQuantities.get(product.getId());
            if (product.isAvailable(boughtQuantity)) {
                product.decreaseQuantity(boughtQuantity);
            } else {
                inventoryCheckedEventPublisher.publish(new InventoryCheckedEvent(command.paymentId(), false));
                throw new InventoryDomainException("Products are not available in stocks.");
            }
        });
        productPort.saveProducts(products);
        inventoryCheckedEventPublisher.publish(new InventoryCheckedEvent(command.paymentId(), true));
        return new OperationResult("Product quantities are updated.", ResultType.SUCCESS);
    }
}
