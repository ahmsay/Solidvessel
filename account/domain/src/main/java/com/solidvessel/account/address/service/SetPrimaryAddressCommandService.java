package com.solidvessel.account.address.service;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.SetPrimaryAddressCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class SetPrimaryAddressCommandService implements CommandService<SetPrimaryAddressCommand, OperationResult> {

    private final AddressQueryPort addressQueryPort;
    private final AddressPort addressPort;
    private final EventPublisher<PrimaryAddressSavedEvent> primaryAddressSavedEventPublisher;

    @Override
    public OperationResult execute(SetPrimaryAddressCommand command) {
        var oldAddress = addressQueryPort.getPrimaryAddress(command.customerId());
        if (oldAddress.getId().equals(command.id())) {
            return new OperationResult("This address is already a primary address.", ResultType.ERROR);
        }
        oldAddress.setNonPrimary();
        var newAddress = addressQueryPort.getByIdAndCustomerId(command.id(), command.customerId());
        newAddress.setPrimary();
        addressPort.save(oldAddress);
        addressPort.save(newAddress);
        primaryAddressSavedEventPublisher.publish(new PrimaryAddressSavedEvent(newAddress.getCustomerId(), newAddress.getFullAddress()));
        return new OperationResult("Your address %s is set to primary.".formatted(newAddress.getName()), ResultType.SUCCESS);
    }
}
