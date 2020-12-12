package com.microshop.paymentservice.services;

import com.microshop.paymentservice.wrapper.Customer;

public interface ICustomerService {

    Customer findById(Long id);
}
