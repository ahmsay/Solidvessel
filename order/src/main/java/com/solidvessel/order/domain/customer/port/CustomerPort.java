package com.solidvessel.order.domain.customer.port;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;

public interface CustomerPort {

    CustomerDataModel getCustomerOfOrder(Long customerId);
}
