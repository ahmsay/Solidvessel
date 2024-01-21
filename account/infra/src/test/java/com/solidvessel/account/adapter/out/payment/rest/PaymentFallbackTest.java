package com.solidvessel.account.adapter.out.payment.rest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentFallbackTest {

    @Test
    void testGetByCustomerIdFallback() {
        var paymentFallback = new PaymentRestClient.PaymentFallback();
        assertEquals(new ArrayList<>(), paymentFallback.getByCustomerId("123", "abc"));
    }
}
