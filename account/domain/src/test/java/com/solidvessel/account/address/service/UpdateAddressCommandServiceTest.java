package com.solidvessel.account.address.service;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Mock
    private EventPublisher<PrimaryAddressSavedEvent> primaryAddressSavedEventPublisher;

    @Test
    void updatePrimaryAddress() {
        var command = new UpdateAddressCommand(1L, "home", "norway", "oslo", "245", "123");
        var commandService = new UpdateAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(true);
        when(addressQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(Address.builder().id(1L).customerId("123").name("home").country("turkey").city("eskisehir").zipCode("26200").isPrimary(true).build());
        commandService.execute(command);
        verify(addressPort).save(Address.builder().id(1L).customerId("123").name("home").country("norway").city("oslo").zipCode("245").isPrimary(true).build());
        verify(primaryAddressSavedEventPublisher).publish(new PrimaryAddressSavedEvent("123", "245 oslo, norway"));
    }

    @Test
    void updateNonPrimaryAddress() {
        var command = new UpdateAddressCommand(1L, "home", "norway", "oslo", "245", "123");
        var commandService = new UpdateAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(true);
        when(addressQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(Address.builder().id(1L).customerId("123").name("home").country("turkey").city("eskisehir").zipCode("26200").isPrimary(false).build());
        commandService.execute(command);
        verify(addressPort).save(Address.builder().id(1L).customerId("123").name("home").country("norway").city("oslo").zipCode("245").isPrimary(false).build());
        verifyNoInteractions(primaryAddressSavedEventPublisher);
    }

    @Test
    void addressIsAlreadyRegistered() {
        var command = new UpdateAddressCommand(1L, "home", "norway", "oslo", "245", "123");
        var commandService = new UpdateAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(false);
        assertThrows(AccountDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(addressPort);
    }
}
