package com.solidvessel.payment.customer.port;

import com.solidvessel.payment.customer.datamodel.CustomerDataModel;

public interface CustomerQueryPort {

    CustomerDataModel getCustomerOfPayment(Long customerId);
}
