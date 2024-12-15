package com.solidvessel.account.address.model;

import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void updateAddress() {
        var address = Address.builder().id(1L).customerId("123").name("home").country("norway").city("oslo").zipCode("473").isPrimary(false).build();
        var command = new UpdateAddressCommand(1L, "work", "turkey", "eskisehir", "26200", "123");
        address.update(command);
        assertEquals(command.id(), address.getId());
        assertEquals(command.name(), address.getName());
        assertEquals(command.country(), address.getCountry());
        assertEquals(command.city(), address.getCity());
        assertEquals(command.zipcode(), address.getZipCode());
        assertEquals(command.customerId(), address.getCustomerId());
    }

    @Test
    void setPrimary() {
        var address = Address.builder().id(1L).customerId("123").isPrimary(false).build();
        address.setPrimary();
        assertTrue(address.getIsPrimary());
    }

    @Test
    void setNonPrimary() {
        var address = Address.builder().id(1L).customerId("123").isPrimary(true).build();
        address.setNonPrimary();
        assertFalse(address.getIsPrimary());
    }
}
