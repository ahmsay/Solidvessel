package com.solidvessel.account.address.service;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.account.common.exception.AccountDomainException;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private AddressPort addressPort;

    @Mock
    private AddressQueryPort addressQueryPort;

    @Mock
    private EventPublisher<PrimaryAddressSavedEvent> primaryAddressSavedEventPublisher;

    @Test
    void addFirstAddress() {
        var command = new AddAddressCommand("home", "norway", "oslo", "245", "123");
        var commandService = new AddAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(false);
        when(addressQueryPort.getAddressCount(command.customerId())).thenReturn(0);
        commandService.execute(command);
        verify(addressPort).save(any(Address.class));
        verify(primaryAddressSavedEventPublisher).publish(any(PrimaryAddressSavedEvent.class));
    }

    @Test
    void addNonFirstAddress() {
        var command = new AddAddressCommand("home", "norway", "oslo", "245", "123");
        var commandService = new AddAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(false);
        when(addressQueryPort.getAddressCount(command.customerId())).thenReturn(1);
        commandService.execute(command);
        verify(addressPort).save(any(Address.class));
        verifyNoInteractions(primaryAddressSavedEventPublisher);
    }

    @Test
    void addressIsAlreadyRegistered() {
        var command = new AddAddressCommand("home", "norway", "oslo", "245", "123");
        var commandService = new AddAddressCommandService(addressPort, addressQueryPort, primaryAddressSavedEventPublisher);
        when(addressQueryPort.isAddressRegistered(command.customerId(), command.name())).thenReturn(true);
        assertThrows(AccountDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(addressPort);
        verifyNoInteractions(primaryAddressSavedEventPublisher);
    }
}
