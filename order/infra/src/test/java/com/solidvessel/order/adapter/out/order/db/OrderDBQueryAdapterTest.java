package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.integrationtest.BaseDatabaseTest;
import com.solidvessel.order.order.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private OrderDBQueryAdapter orderDBQueryAdapter;

    @Test
    public void getAll() {
        persistEntity(new OrderJpaEntity(null, OrderStatus.DELIVERED, "123", 1L));
        persistEntity(new OrderJpaEntity(null, OrderStatus.ON_THE_WAY, "456", 5L));
        var orders = orderDBQueryAdapter.getAll();
        assertEquals(OrderStatus.DELIVERED, orders.getFirst().status());
        assertEquals(OrderStatus.ON_THE_WAY, orders.get(1).status());
    }

    @Test
    public void getById() {
        var order = persistEntity(new OrderJpaEntity(null, OrderStatus.DELIVERED, "123", 12L));
        assertEquals(12L, orderDBQueryAdapter.getById(order.getId()).paymentId());
    }

    @Test
    public void getCustomerId() {
        persistEntity(new OrderJpaEntity(null, OrderStatus.PREPARING, "123", 12L));
        var ordersOfCustomer = orderDBQueryAdapter.getByCustomerId("123");
        assertEquals(OrderStatus.PREPARING, ordersOfCustomer.getFirst().status());
    }
}
