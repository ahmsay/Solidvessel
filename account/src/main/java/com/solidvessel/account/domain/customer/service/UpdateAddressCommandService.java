package com.solidvessel.account.domain.customer.service;

import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.domain.customer.service.command.UpdateAddressCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAddressCommandService implements CommandService<UpdateAddressCommand> {

    private final AddressPort addressPort;

    @Override
    public OperationResult execute(UpdateAddressCommand command) {
        Long customerId = SessionUtil.getCurrentUserId();
        if (!addressPort.isAddressRegistered(customerId, command.name())) {
            return new OperationResult("Address is not registered.", ResultType.ERROR);
        }
        addressPort.updateAddress(customerId, command.toDomainModel());
        return new OperationResult("Address is updated.", ResultType.SUCCESS);
    }
}
