package com.solidvessel.order.order.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderTest {

    @Test
    void createNewOrder() {
        var order = Order.newOrder("123", 1L);
        assertNull(order.getId());
        assertEquals(OrderStatus.PREPARING, order.getStatus());
        assertEquals("123", order.getCustomerId());
        assertEquals(1L, order.getPaymentId());
    }
}
