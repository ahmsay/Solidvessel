package com.solidvessel.account.address.model;

import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void updateAddress() {
        var address = Address.builder().id(1L).customerId("123").name("home").country("norway").city("oslo").zipCode("473").build();
        var command = new UpdateAddressCommand(1L, "work", "turkey", "eskisehir", "26200", "123");
        address.update(command);
        assertEquals(command.id(), address.getId());
        assertEquals(command.name(), address.getName());
        assertEquals(command.country(), address.getCountry());
        assertEquals(command.city(), address.getCity());
        assertEquals(command.zipcode(), address.getZipCode());
        assertEquals(command.customerId(), address.getCustomerId());
    }
}
