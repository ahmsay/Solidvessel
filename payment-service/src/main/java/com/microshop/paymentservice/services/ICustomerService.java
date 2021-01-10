package com.microshop.paymentservice.services;

import com.microshop.paymentservice.wrapper.CustomerDTO;

public interface ICustomerService {

    CustomerDTO findById(Long id);
}
