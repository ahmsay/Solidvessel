package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.service.command.RemoveAddressCommand;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RemoveAddressCommandServiceTest {

    @Mock
    private AddressPort addressPort;

    @Test
    void removeAddress() {
        var command = new RemoveAddressCommand(1L, "123");
        var commandService = new RemoveAddressCommandService(addressPort);
        var operationResult = commandService.execute(command);
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }
}
