package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.model.Customer;

import java.util.List;

public interface CustomerPort {

    List<CustomerDataModel> getAll();

    CustomerDataModel getById(Long id);

    void save(Customer customer);
}
