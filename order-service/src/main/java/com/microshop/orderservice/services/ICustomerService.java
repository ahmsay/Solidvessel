package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Customer;

public interface ICustomerService {

    Customer findById(Long id);
}
