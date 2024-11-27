package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.DeleteProductsCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteProductsCommandService implements CommandService<DeleteProductsCommand, OperationResult> {

    private final ProductPort productPort;

    @Override
    public OperationResult execute(DeleteProductsCommand command) {
        productPort.deleteByIds(command.productIds());
        return new OperationResult("Products are deleted successfully.", ResultType.SUCCESS);
    }
}
