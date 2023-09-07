package com.solidvessel.order.domain.order.port;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;

import java.util.List;

public interface OrderQueryPort {

    List<OrderDataModel> getAll();

    OrderDataModel getById(Long id);

    List<OrderDataModel> getByCustomerId(Long customerId);
}
