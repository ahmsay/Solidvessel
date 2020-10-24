package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;

public interface ICustomerService {

    Customer findCustomerOfOrder(Long orderId);
}
