package com.solidvessel.order.order.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderTest {

    @Test
    void createNewOrder() {
        var order1 = Order.newOrder("123", 1L, "952 baku, azerbaijan");
        assertNull(order1.getId());
        assertEquals(OrderStatus.PREPARING, order1.getStatus());
        assertEquals("123", order1.getCustomerId());
        assertEquals(1L, order1.getPaymentId());

        var order2 = new Order(OrderStatus.DELIVERED, "456", 2L, "592 milano, italy");
        assertNull(order2.getId());
        assertEquals(OrderStatus.DELIVERED, order2.getStatus());
        assertEquals("456", order2.getCustomerId());
        assertEquals(2L, order2.getPaymentId());
    }
}
