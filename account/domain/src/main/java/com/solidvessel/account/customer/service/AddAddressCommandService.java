package com.solidvessel.account.customer.service;

import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.account.customer.port.AddressPort;
import com.solidvessel.account.customer.port.AddressQueryPort;
import com.solidvessel.account.customer.service.command.AddAddressCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.DomainComponent;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddAddressCommandService implements CommandService<AddAddressCommand> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;

    @Override
    public OperationResult execute(AddAddressCommand command) {
        checkIfAddressIsAlreadyRegistered(command);
        addressPort.addAddress(command.customerId(), command.toDomainModel());
        return new OperationResult("Address is added.", ResultType.SUCCESS);
    }

    private void checkIfAddressIsAlreadyRegistered(AddAddressCommand command) {
        if (addressQueryPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new AccountDomainException("The address with the same name is already added.");
        }
    }
}
