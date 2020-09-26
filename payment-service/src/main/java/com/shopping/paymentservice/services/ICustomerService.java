package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;

public interface ICustomerService {

    Customer getCustomerOfPayment(long paymentId);
}
