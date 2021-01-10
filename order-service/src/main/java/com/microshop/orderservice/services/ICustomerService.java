package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.CustomerDTO;

public interface ICustomerService {

    CustomerDTO findById(Long id);
}
