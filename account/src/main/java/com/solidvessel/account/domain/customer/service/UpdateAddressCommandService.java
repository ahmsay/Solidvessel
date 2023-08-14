package com.solidvessel.account.domain.customer.service;

import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.domain.customer.service.command.UpdateAddressCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAddressCommandService implements CommandService<UpdateAddressCommand> {

    private final AddressPort addressPort;

    @Override
    public OperationResult execute(UpdateAddressCommand command) {
        checkIfAddressIsRegistered(command);
        addressPort.updateAddress(command.customerId(), command.toDomainModel());
        return new OperationResult("Address is updated.", ResultType.SUCCESS);
    }

    private void checkIfAddressIsRegistered(UpdateAddressCommand command) {
        if (!addressPort.isAddressRegistered(command.customerId(), command.name())) {
            throw new RuntimeException("Address is not registered.");
        }
    }
}
