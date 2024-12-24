package com.solidvessel.payment.customer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    void updateAddress() {
        var customer = new Customer("123", "32572 New Jersey, USA");
        customer.updateAddress("5742 Köln, Germany");
        assertEquals("5742 Köln, Germany", customer.getAddress());
        assertEquals("123", customer.getId());
    }
}
