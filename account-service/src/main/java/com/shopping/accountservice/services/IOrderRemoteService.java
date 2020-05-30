package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Order;

import java.util.Set;

public interface IOrderRemoteService {

    Set<Order> getOrdersOfCustomer(String customerId);
}
