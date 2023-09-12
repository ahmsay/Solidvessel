package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
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
        productPort.save(command.toDomainModel());
        return new OperationResult("Product is added.", ResultType.SUCCESS);
    }
}
