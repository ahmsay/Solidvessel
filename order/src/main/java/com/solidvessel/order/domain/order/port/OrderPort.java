package com.solidvessel.order.domain.order.port;

import com.solidvessel.order.domain.order.model.Order;

public interface OrderPort {

    void save(Order order);
}
