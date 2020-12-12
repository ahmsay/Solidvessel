package com.microshop.accountservice.services;

import com.microshop.accountservice.wrapper.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findByCustomerId(Long customerId);
}
