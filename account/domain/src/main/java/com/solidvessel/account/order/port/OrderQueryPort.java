package com.solidvessel.account.order.port;

import com.solidvessel.account.order.datamodel.OrderDataModel;

import java.util.List;

public interface OrderQueryPort {

    List<OrderDataModel> getOrdersOfCustomer(Long customerId);
}
