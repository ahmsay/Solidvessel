package com.solidvessel.account.address.service;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.SetPrimaryAddressCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SetPrimaryAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Mock
    private EventPublisher<PrimaryAddressSavedEvent> primaryAddressSavedEventPublisher;

    @Test
    void setPrimaryAddress() {
        var command = new SetPrimaryAddressCommand(1L, "123");
        var commandService = new SetPrimaryAddressCommandService(addressQueryPort, addressPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.getPrimaryAddress("123")).thenReturn(Address.builder().id(2L).isPrimary(true).build());
        when(addressQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(Address.builder().id(1L).isPrimary(false).build());
        var result = commandService.execute(command);
        verify(addressPort).save(Address.builder().id(2L).isPrimary(false).build());
        verify(addressPort).save(Address.builder().id(1L).isPrimary(true).build());
        verify(primaryAddressSavedEventPublisher).publish(any(PrimaryAddressSavedEvent.class));
        assertEquals(ResultType.SUCCESS, result.resultType());
    }

    @Test
    void addressIsAlreadyPrimary() {
        var command = new SetPrimaryAddressCommand(1L, "123");
        var commandService = new SetPrimaryAddressCommandService(addressQueryPort, addressPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.getPrimaryAddress("123")).thenReturn(Address.builder().id(1L).isPrimary(true).build());
        var result = commandService.execute(command);
        verifyNoInteractions(addressPort);
        assertEquals(ResultType.ERROR, result.resultType());
        verifyNoInteractions(primaryAddressSavedEventPublisher);
    }
}
