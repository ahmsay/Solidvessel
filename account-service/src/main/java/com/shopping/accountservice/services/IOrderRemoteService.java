package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Order;

import java.util.List;

public interface IOrderRemoteService {

    List<Order> getOrdersOfCustomer(String id);
}
