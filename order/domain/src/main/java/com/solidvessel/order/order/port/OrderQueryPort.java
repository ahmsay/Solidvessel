package com.solidvessel.order.order.port;

import com.solidvessel.order.order.datamodel.OrderDataModel;

import java.util.List;

public interface OrderQueryPort {

    List<OrderDataModel> getAll();

    OrderDataModel getById(Long id);

    List<OrderDataModel> getByCustomerId(String customerId);
}
