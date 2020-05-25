package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;

public interface ICustomerRemoteService {

    Customer getCustomerOfOrder(String id);
}
