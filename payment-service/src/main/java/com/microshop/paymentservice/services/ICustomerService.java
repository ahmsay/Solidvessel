package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;

public interface ICustomerService {

    CustomerDTO findById(Long id);
}
