package com.solidvessel.account.adapter.out.order.rest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderFallbackTest {

    @Test
    void testGetByCustomerId() {
        var orderFallback = new OrderRestClient.OrderFallback();
        assertEquals(new ArrayList<>(), orderFallback.getByCustomerId("123", "abc"));
    }
}
