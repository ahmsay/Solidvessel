package com.solidvessel.payment.customer.port;

import com.solidvessel.payment.customer.model.Customer;

public interface CustomerQueryPort {

    Customer getCustomerOfPayment(String customerId);
}
