package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.infra.adapter.customer.rest.response.CustomerResponse;

import java.util.List;

public interface CustomerPort {

    List<CustomerResponse> getAll();

    CustomerResponse getById(Long id);

    void add(Customer customer);
}
