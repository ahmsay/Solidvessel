package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;

import java.util.List;

public interface CustomerQueryPort {

    List<CustomerDataModel> getAll();

    CustomerDataModel getById(Long id);
}
