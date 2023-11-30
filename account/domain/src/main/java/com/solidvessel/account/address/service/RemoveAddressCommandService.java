package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.service.command.RemoveAddressCommand;
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
        addressPort.delete(command.id(), command.customerId());
        return new OperationResult("Address is removed.", ResultType.SUCCESS);
    }
}
