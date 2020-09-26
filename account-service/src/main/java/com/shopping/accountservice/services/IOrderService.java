package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getOrdersOfCustomer(long customerId);
}
