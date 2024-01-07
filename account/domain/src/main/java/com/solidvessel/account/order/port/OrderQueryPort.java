package com.solidvessel.account.order.port;

import com.solidvessel.account.order.model.Order;

import java.util.List;

public interface OrderQueryPort {

    List<Order> getOrdersOfCustomer(String customerId);
}
