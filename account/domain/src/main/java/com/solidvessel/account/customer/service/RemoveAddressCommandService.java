package com.solidvessel.account.customer.service;

import com.solidvessel.account.customer.port.AddressPort;
import com.solidvessel.account.customer.service.command.RemoveAddressCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class RemoveAddressCommandService implements CommandService<RemoveAddressCommand> {

    private final AddressPort addressPort;

    @Override
    public OperationResult execute(RemoveAddressCommand command) {
        Long customerId = command.customerId();
        addressPort.removeAddress(customerId, command.name());
        return new OperationResult("Address is removed.", ResultType.SUCCESS);
    }
}
