package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO findById(Long id);
}
