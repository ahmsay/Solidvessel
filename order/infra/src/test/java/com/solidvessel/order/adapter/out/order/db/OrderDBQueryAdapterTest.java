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
        persistEntity(new OrderJpaEntity(OrderStatus.DELIVERED, "123", 1L));
        persistEntity(new OrderJpaEntity(OrderStatus.ON_THE_WAY, "456", 5L));
        var orders = orderDBQueryAdapter.getAll();
        assertEquals(OrderStatus.DELIVERED, orders.getFirst().getStatus());
        assertEquals(OrderStatus.ON_THE_WAY, orders.get(1).getStatus());
    }

    @Test
    public void getById() {
        var orderJpaEntity = persistEntity(new OrderJpaEntity(OrderStatus.DELIVERED, "123", 12L));
        var order = orderDBQueryAdapter.getById(orderJpaEntity.getId());
        assertEquals(orderJpaEntity.getId(), order.getId());
        assertEquals(orderJpaEntity.getCustomerId(), order.getCustomerId());
        assertEquals(orderJpaEntity.getStatus(), order.getStatus());
        assertEquals(orderJpaEntity.getPaymentId(), order.getPaymentId());
    }

    @Test
    public void getCustomerId() {
        persistEntity(new OrderJpaEntity(OrderStatus.PREPARING, "123", 12L));
        var ordersOfCustomer = orderDBQueryAdapter.getByCustomerId("123");
        assertEquals(OrderStatus.PREPARING, ordersOfCustomer.getFirst().getStatus());
    }
}
