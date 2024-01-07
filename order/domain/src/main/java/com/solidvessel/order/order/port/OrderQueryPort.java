package com.solidvessel.order.order.port;

import com.solidvessel.order.order.model.Order;

import java.util.List;

public interface OrderQueryPort {

    List<Order> getAll();

    Order getById(Long id);

    List<Order> getByCustomerId(String customerId);
}
