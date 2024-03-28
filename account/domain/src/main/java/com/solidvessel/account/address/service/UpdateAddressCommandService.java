package com.solidvessel.account.address.service;

import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdateAddressCommandService implements CommandService<UpdateAddressCommand, Address> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;

    @Override
    public Address execute(UpdateAddressCommand command) {
        checkIfAddressIsRegistered(command);
        Address currentAddress = addressQueryPort.getByIdAndCustomerId(command.id(), command.customerId());
        currentAddress.update(command);
        return addressPort.save(currentAddress);
    }

    private void checkIfAddressIsRegistered(UpdateAddressCommand command) {
        if (!addressQueryPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new AccountDomainException("Address is not registered.");
        }
    }
}
