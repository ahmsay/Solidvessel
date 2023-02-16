package com.solidvessel.payment.domain.customer.port;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;

public interface CustomerPort {

    CustomerDataModel getCustomerOfPayment(Long customerId, String session);
}
