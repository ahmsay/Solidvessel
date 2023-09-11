package com.solidvessel.account.customer.port;

import com.solidvessel.account.customer.datamodel.CustomerDataModel;

import java.util.List;

public interface CustomerQueryPort {

    List<CustomerDataModel> getAll();

    CustomerDataModel getById(Long id);
}
