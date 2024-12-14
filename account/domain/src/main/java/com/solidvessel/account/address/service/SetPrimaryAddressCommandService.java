package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.SetPrimaryAddressCommand;
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

    @Override
    public OperationResult execute(SetPrimaryAddressCommand command) {
        var address = addressQueryPort.getByIdAndCustomerId(command.id(), command.customerId());
        if (address.getIsPrimary()) {
            return new OperationResult("This address is already a primary address.", ResultType.ERROR);
        }
        address.setPrimary();
        addressPort.save(address);
        return new OperationResult("Your address %s is set to primary.".formatted(address.getName()), ResultType.SUCCESS);
    }
}
