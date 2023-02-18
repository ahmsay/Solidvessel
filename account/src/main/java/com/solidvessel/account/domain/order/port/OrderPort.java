package com.solidvessel.account.domain.order.port;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;

import java.util.List;

public interface OrderPort {

    List<OrderDataModel> getOrdersOfCustomer(Long customerId);
}
