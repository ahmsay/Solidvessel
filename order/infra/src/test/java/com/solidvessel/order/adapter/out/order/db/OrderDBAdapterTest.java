package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.integrationtest.BaseDatabaseTest;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private OrderDBAdapter orderDBAdapter;

    @Test
    void saveOrder() {
        var order = Order.builder().customerId("123").paymentId(1L).status(OrderStatus.PREPARING).address("481 tokyo, japan").build();
        orderDBAdapter.save(order);
    }
}
