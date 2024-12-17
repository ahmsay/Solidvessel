package com.solidvessel.account.address.service;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddAddressCommandService implements CommandService<AddAddressCommand, Address> {

    private final AddressPort addressPort;
    private final AddressQueryPort addressQueryPort;
    private final EventPublisher<PrimaryAddressSavedEvent> primaryAddressSavedEventPublisher;

    @Override
    public Address execute(AddAddressCommand command) {
        var address = command.toDomainModel();
        if (addressQueryPort.getAddressCount(command.customerId()) == 0) {
            address.setPrimary();
        }
        checkIfAddressIsAlreadyRegistered(command);
        handlePrimaryAddressEvent(address);
        return addressPort.save(address);
    }

    private void checkIfAddressIsAlreadyRegistered(AddAddressCommand command) {
        if (addressQueryPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new AccountDomainException("The address with the same name is already added.");
        }
    }

    private void handlePrimaryAddressEvent(Address address) {
        if (address.getIsPrimary()) {
            primaryAddressSavedEventPublisher.publish(new PrimaryAddressSavedEvent(address.getCustomerId(), address.getFullAddress()));
        }
    }
}
