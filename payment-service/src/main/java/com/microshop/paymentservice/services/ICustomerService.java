package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Customer;

public interface ICustomerService {

    Customer findCustomerOfPayment(Long customerId);
}
