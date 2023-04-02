package com.solidvessel.account.domain.customer.service;

import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.domain.customer.service.command.AddAddressCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAddressCommandService implements CommandService<AddAddressCommand> {

    private final AddressPort addressPort;

    @Override
    public OperationResult execute(AddAddressCommand command) {
        Long customerId = SessionUtil.getCurrentUserId();
        if (addressPort.isAddressRegistered(customerId, command.name())) {
            return new OperationResult("The address with the same name is already added.", ResultType.ERROR);
        }
        addressPort.addAddress(customerId, command.toDomainModel());
        return new OperationResult("Address is added.", ResultType.SUCCESS);
    }
}
