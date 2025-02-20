package com.solidvessel.order.order.port;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.shared.query.QueryOptions;

import java.util.List;

public interface OrderQueryPort {

    List<Order> getOrders(QueryOptions queryOptions);

    Order getById(Long id);

    List<Order> getByCustomerId(String customerId);

    Order getByIdAndCustomerId(Long id, String customerId);
}
