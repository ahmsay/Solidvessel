package com.solidvessel.account.address.model;

import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void updateAddress() {
        Address address = new Address(1L, "123", "home", "norway", "oslo", "473");
        UpdateAddressCommand command = new UpdateAddressCommand(1L, "work", "turkey", "eskisehir", "26200", "123");
        address.update(command);
        assertEquals(command.id(), address.getId());
        assertEquals(command.name(), address.getName());
        assertEquals(command.country(), address.getCountry());
        assertEquals(command.city(), address.getCity());
        assertEquals(command.zipcode(), address.getZipCode());
        assertEquals(command.customerId(), address.getCustomerId());
    }
}
