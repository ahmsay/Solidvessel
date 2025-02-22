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
        assertEquals("952 baku, azerbaijan", order1.getAddress());
        assertNull(order1.getCancellation());

        var order2 = new Order(OrderStatus.DELIVERED, "456", 2L, "592 milano, italy", null);
        assertNull(order2.getId());
        assertEquals(OrderStatus.DELIVERED, order2.getStatus());
        assertEquals("456", order2.getCustomerId());
        assertEquals(2L, order2.getPaymentId());
    }

    @Test
    void cancel() {
        var order = Order.newOrder("123", 1L, "952 baku, azerbaijan");
        order.cancel(CancellationReason.DONT_NEED_ANYMORE, "I don't need this thing more.");
        assertEquals(CancellationReason.DONT_NEED_ANYMORE, order.getCancellation().cancellationReason());
    }
}
