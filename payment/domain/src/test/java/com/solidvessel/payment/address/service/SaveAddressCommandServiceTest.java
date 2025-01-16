package com.solidvessel.payment.address.service;

import com.solidvessel.payment.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerPort;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private CustomerPort customerPort;

    @Mock
    private CustomerQueryPort customerQueryPort;

    @Test
    void saveNewAddress() {
        var event = new PrimaryAddressSavedEvent("123", "6493 Oslo, Norway");
        var commandService = new SaveAddressCommandService(customerPort, customerQueryPort);
        when(customerQueryPort.getById("123")).thenReturn(Optional.empty());
        commandService.execute(event);
        verify(customerPort).save(new Customer("123", "6493 Oslo, Norway"));
    }

    @Test
    void updateExistingAddress() {
        var event = new PrimaryAddressSavedEvent("123", "6493 Oslo, Norway");
        var commandService = new SaveAddressCommandService(customerPort, customerQueryPort);
        when(customerQueryPort.getById("123")).thenReturn(Optional.of(new Customer("123", "4341 Helsinki, Finland")));
        commandService.execute(event);
        verify(customerPort).save(new Customer("123", "6493 Oslo, Norway"));
    }
}
