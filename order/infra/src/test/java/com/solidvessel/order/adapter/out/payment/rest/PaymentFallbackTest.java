package com.solidvessel.order.adapter.out.payment.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentFallbackTest {

    @Test
    void testGetPaymentByIdFallback() {
        var paymentFallback = new PaymentRestClient.PaymentFallback();
        assertNull(paymentFallback.getById(1L, "abc"));
    }
}
