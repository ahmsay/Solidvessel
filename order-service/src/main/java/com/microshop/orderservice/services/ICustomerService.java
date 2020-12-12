package com.microshop.orderservice.services;

import com.microshop.orderservice.wrapper.Customer;

public interface ICustomerService {

    Customer findById(Long id);
}
