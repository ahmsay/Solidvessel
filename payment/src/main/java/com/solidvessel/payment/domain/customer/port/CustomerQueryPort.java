package com.solidvessel.payment.domain.customer.port;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;

public interface CustomerQueryPort {

    CustomerDataModel getCustomerOfPayment(Long customerId);
}
