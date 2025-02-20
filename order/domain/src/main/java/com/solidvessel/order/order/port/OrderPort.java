package com.solidvessel.order.order.port;

import com.solidvessel.order.order.model.Order;

public interface OrderPort {

    void create(Order order);

    void save(Order order);
}
