package com.solidvessel.account.address.service;

import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddAddressCommandServiceTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Test
    void addAddress() {
        var command = new AddAddressCommand("home", "norway", "oslo", "245", "123");
        var commandService = new AddAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(anyString(), anyString())).thenReturn(false);
        var operationResult = commandService.execute(command);
        verify(addressPort).save(any(Address.class));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void addressIsAlreadyRegistered() {
        var command = new AddAddressCommand("home", "norway", "oslo", "245", "123");
        var commandService = new AddAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(anyString(), anyString())).thenReturn(true);
        assertThrows(AccountDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(addressPort);
    }
}
