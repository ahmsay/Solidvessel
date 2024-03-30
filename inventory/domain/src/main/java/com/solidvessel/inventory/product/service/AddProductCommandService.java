package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddProductCommandService implements CommandService<AddProductCommand, Product> {

    private final ProductPort productPort;

    @Override
    public Product execute(AddProductCommand command) {
        return productPort.save(command.toDomainModel());
    }
}
