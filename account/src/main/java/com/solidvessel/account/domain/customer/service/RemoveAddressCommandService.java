package com.solidvessel.account.domain.customer.service;

import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.domain.customer.service.command.RemoveAddressCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveAddressCommandService implements CommandService<RemoveAddressCommand> {

    private final AddressPort addressPort;

    @Override
    public OperationResult execute(RemoveAddressCommand command) {
        Long customerId = SessionUtil.getCurrentUserId();
        addressPort.removeAddress(customerId, command.name());
        return new OperationResult("Address is removed.", ResultType.SUCCESS);
    }
}
