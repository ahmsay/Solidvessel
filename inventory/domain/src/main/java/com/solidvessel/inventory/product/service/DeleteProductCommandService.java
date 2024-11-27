package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.DeleteProductCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteProductCommandService implements CommandService<DeleteProductCommand, OperationResult> {

    private final ProductPort productPort;

    @Override
    public OperationResult execute(DeleteProductCommand command) {
        productPort.delete(command.productId());
        return new OperationResult("Product is deleted successfully.", ResultType.SUCCESS);
    }
}
