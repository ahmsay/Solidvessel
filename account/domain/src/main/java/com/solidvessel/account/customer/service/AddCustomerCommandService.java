package com.solidvessel.account.customer.service;

import com.solidvessel.account.customer.port.CustomerPort;
import com.solidvessel.account.customer.service.command.AddCustomerCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCustomerCommandService implements CommandService<AddCustomerCommand> {

    private final CustomerPort customerPort;

    @Override
    public OperationResult execute(AddCustomerCommand command) {
        customerPort.save(command.toDomainModel());
        return new OperationResult("Customer is added.", ResultType.SUCCESS);
    }
}
