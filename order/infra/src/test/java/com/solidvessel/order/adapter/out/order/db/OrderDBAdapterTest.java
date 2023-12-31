package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.integrationtest.BaseDatabaseTest;
import com.solidvessel.order.order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private OrderDBAdapter orderDBAdapter;

    @Test
    public void saveOrder() {
        var order = Order.newOrder("123", 1L);
        orderDBAdapter.save(order);
    }
}
