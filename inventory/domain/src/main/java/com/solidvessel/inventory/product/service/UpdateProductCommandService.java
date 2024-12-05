package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdateProductCommandService implements CommandService<UpdateProductCommand, Product> {

    private final ProductQueryPort productQueryPort;
    private final ProductPort productPort;

    @Override
    public Product execute(UpdateProductCommand command) {
        var product = productQueryPort.getById(command.id());
        product.update(command);
        return productPort.save(product);
    }
}
