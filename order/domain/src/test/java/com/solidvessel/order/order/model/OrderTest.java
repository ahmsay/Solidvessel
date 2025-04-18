package com.solidvessel.order.order.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

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

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"PREPARING", "ON_THE_WAY"})
    void canCancel(OrderStatus status) {
        var order = Order.builder().id(1L).status(status).build();
        assertTrue(order.canCancel());
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"DELIVERED", "CANCELLED"})
    void canNotCancel(OrderStatus status) {
        var order = Order.builder().id(1L).status(status).build();
        assertFalse(order.canCancel());
    }

    @Test
    void cancel() {
        var order = Order.newOrder("123", 1L, "952 baku, azerbaijan");
        order.cancel(CancellationReason.DONT_NEED_ANYMORE, "I don't need this thing more.");
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertEquals(CancellationReason.DONT_NEED_ANYMORE.toString(), order.getCancellation().cancellationReason().toString());
    }
}
