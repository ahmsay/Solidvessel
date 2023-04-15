package com.solidvessel.inventory.domain.product.service;

import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProductCommandService implements CommandService<AddProductCommand> {

    private final ProductPort productPort;

    @Override
    public OperationResult execute(AddProductCommand command) {
        productPort.save(Product.newProduct(command.name(), command.price(), command.category()));
        return new OperationResult("Product is added.", ResultType.SUCCESS);
    }
}
