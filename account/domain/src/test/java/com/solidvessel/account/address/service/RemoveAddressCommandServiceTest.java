package com.solidvessel.account.address.service;

import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.RemoveAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RemoveAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Test
    void removeAddress() {
        var command = new RemoveAddressCommand(1L, "123");
        var commandService = new RemoveAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(command.id(), command.customerId())).thenReturn(true);
        var operationResult = commandService.execute(command);
        verify(addressPort).delete(1L);
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void addressIsAlreadyRegistered() {
        var command = new RemoveAddressCommand(1L, "123");
        var commandService = new RemoveAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(command.id(), command.customerId())).thenReturn(false);
        assertThrows(AccountDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(addressPort);
    }
}
