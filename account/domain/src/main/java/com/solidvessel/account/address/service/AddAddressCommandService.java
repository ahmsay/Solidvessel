package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddAddressCommandService implements CommandService<AddAddressCommand> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;

    @Override
    public OperationResult execute(AddAddressCommand command) {
        checkIfAddressIsAlreadyRegistered(command);
        addressPort.save(command.toDomainModel(command.customerId()));
        return new OperationResult("Address is added.", ResultType.SUCCESS);
    }

    private void checkIfAddressIsAlreadyRegistered(AddAddressCommand command) {
        if (addressQueryPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new AccountDomainException("The address with the same name is already added.");
        }
    }
}
