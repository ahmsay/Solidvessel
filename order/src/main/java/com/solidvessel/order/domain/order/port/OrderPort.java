package com.solidvessel.order.domain.order.port;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.model.Order;

import java.util.List;

public interface OrderPort {

    List<OrderDataModel> getAll();

    OrderDataModel getById(Long id);

    List<OrderDataModel> getByCustomerId(Long customerId);

    void save(Order order);
}
