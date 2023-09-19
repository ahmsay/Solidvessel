package com.solidvessel.account.customer.service;

import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.account.customer.port.AddressPort;
import com.solidvessel.account.customer.port.AddressQueryPort;
import com.solidvessel.account.customer.service.command.UpdateAddressCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdateAddressCommandService implements CommandService<UpdateAddressCommand> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;

    @Override
    public OperationResult execute(UpdateAddressCommand command) {
        checkIfAddressIsRegistered(command);
        addressPort.updateAddress(command.customerId(), command.toDomainModel());
        return new OperationResult("Address is updated.", ResultType.SUCCESS);
    }

    private void checkIfAddressIsRegistered(UpdateAddressCommand command) {
        if (!addressQueryPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new AccountDomainException("Address is not registered.");
        }
    }
}