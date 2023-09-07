package com.solidvessel.order.domain.customer.port;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;

public interface CustomerQueryPort {

    CustomerDataModel getCustomerOfOrder(Long customerId);
}
