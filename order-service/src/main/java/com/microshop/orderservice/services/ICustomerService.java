package com.microshop.orderservice.services;

import com.microshop.orderservice.wrapper.CustomerDTO;

public interface ICustomerService {

    CustomerDTO findById(Long id);
}
