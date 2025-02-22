package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.integrationtest.BaseDatabaseTest;
import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.shared.query.QueryOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private OrderDBQueryAdapter orderDBQueryAdapter;

    @Test
    void getProducts() {
        persistEntity(new OrderJpaEntity(OrderStatus.DELIVERED, "123", 1L, "6904 lizbon, portugal", null, null));
        persistEntity(new OrderJpaEntity(OrderStatus.ON_THE_WAY, "456", 5L, "4814 sao paulo, brazil", null, null));
        var orders = orderDBQueryAdapter.getOrders(new QueryOptions(0));
        assertEquals(OrderStatus.DELIVERED, orders.getFirst().getStatus());
        assertEquals(OrderStatus.ON_THE_WAY, orders.get(1).getStatus());
    }

    @Test
    void getById() {
        var orderJpaEntity = persistEntity(new OrderJpaEntity(OrderStatus.DELIVERED, "123", 12L, "260 eskisehir, türkiye", null, null));
        var order = orderDBQueryAdapter.getById(orderJpaEntity.getId());
        assertEquals(orderJpaEntity.getId(), order.getId());
        assertEquals(orderJpaEntity.getCustomerId(), order.getCustomerId());
        assertEquals(orderJpaEntity.getStatus(), order.getStatus());
        assertEquals(orderJpaEntity.getPaymentId(), order.getPaymentId());
        assertEquals(orderJpaEntity.getAddress(), order.getAddress());
        assertEquals(orderJpaEntity.getCancellationReason(), order.getCancellation().cancellationReason());
        assertEquals(orderJpaEntity.getCancellationExplanation(), order.getCancellation().explanation());
    }

    @Test
    void getByCustomerId() {
        persistEntity(new OrderJpaEntity(OrderStatus.PREPARING, "123", 12L, "4913 baku, azerbaijan", null, null));
        var ordersOfCustomer = orderDBQueryAdapter.getByCustomerId("123");
        assertEquals(OrderStatus.PREPARING, ordersOfCustomer.getFirst().getStatus());
    }

    @Test
    void getByIdAndCustomerId() {
        var orderJpaEntity = persistEntity(new OrderJpaEntity(OrderStatus.ON_THE_WAY, "123", 5L, "8582 florence, italy", CancellationReason.FOUND_BETTER_ALTERNATIVE, "asdasd"));
        var order = orderDBQueryAdapter.getByIdAndCustomerId(orderJpaEntity.getId(), "123");
        assertEquals(OrderStatus.ON_THE_WAY, order.getStatus());
    }
}
