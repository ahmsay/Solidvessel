package com.solidvessel.order.customer.port;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;

public interface CustomerQueryPort {

    CustomerDataModel getCustomerOfOrder(Long customerId);
}
