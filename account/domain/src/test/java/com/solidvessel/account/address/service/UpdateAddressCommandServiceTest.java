package com.solidvessel.account.address.service;

import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Test
    void updateAddress() {
        var command = new UpdateAddressCommand(1L, "home", "norway", "oslo", "245", "123");
        var commandService = new UpdateAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(true);
        when(addressQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(new Address(1L, "home", "123", "turkey", "eskisehir", "26200"));
        var operationResult = commandService.execute(command);
        verify(addressPort).save(any(Address.class));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void addressIsAlreadyRegistered() {
        var command = new UpdateAddressCommand(1L, "home", "norway", "oslo", "245", "123");
        var commandService = new UpdateAddressCommandService(addressPort, addressQueryPort);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(false);
        assertThrows(AccountDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(addressPort);
    }
}
