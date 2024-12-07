package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.ProductAvailability;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.ChangeProductAvailabilityCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ChangeProductAvailabilityCommandService implements CommandService<ChangeProductAvailabilityCommand, ProductAvailability> {

    private final ProductQueryPort productQueryPort;
    private final ProductPort productPort;

    @Override
    public ProductAvailability execute(ChangeProductAvailabilityCommand command) {
        var product = productQueryPort.getById(command.id());
        product.changeAvailability(command.isAvailable());
        productPort.save(product);
        return product.isAvailable(0);
    }
}
