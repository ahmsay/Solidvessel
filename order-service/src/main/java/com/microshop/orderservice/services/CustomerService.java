package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO findById(Long id);
}
