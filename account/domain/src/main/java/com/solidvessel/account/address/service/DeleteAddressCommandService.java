package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.DeleteAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeleteAddressCommandService implements CommandService<DeleteAddressCommand, OperationResult> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;

    @Override
    public OperationResult execute(DeleteAddressCommand command) {
        checkIfAddressIsRegistered(command);
        addressPort.delete(command.id(), command.customerId());
        return new OperationResult("Address is removed.", ResultType.SUCCESS);
    }

    private void checkIfAddressIsRegistered(DeleteAddressCommand command) {
        if (!addressQueryPort.isAddressRegistered(command.id(), command.customerId())) {
            throw new AccountDomainException("Address is not registered.");
        }
    }
}
