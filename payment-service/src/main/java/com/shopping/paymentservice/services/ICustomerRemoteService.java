package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;

public interface ICustomerRemoteService {

    Customer getCustomerOfPayment(String paymentId);
}
