package com.solidvessel.account.domain.order.port;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;

import java.util.List;

public interface OrderQueryPort {

    List<OrderDataModel> getOrdersOfCustomer(Long customerId);
}
