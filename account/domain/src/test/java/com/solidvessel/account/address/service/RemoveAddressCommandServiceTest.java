package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.service.command.RemoveAddressCommand;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class RemoveAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Test
    void removeAddress() {
        var command = new RemoveAddressCommand(1L, "123");
        var commandService = new RemoveAddressCommandService(addressPort);
        var operationResult = commandService.execute(command);
        verify(addressPort).delete(1L, "123");
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }
}
