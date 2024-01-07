package com.solidvessel.order.customer.port;

import com.solidvessel.order.customer.model.Customer;

public interface CustomerQueryPort {

    Customer getCustomerOfOrder(String customerId);
}
