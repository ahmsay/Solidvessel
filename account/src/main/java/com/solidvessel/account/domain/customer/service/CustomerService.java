package com.solidvessel.account.domain.customer.service;

import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerPort customerPort;

    public CustomerService(final CustomerPort customerPort) {
        this.customerPort = customerPort;
    }

    public void add(final Customer customer) {
        customerPort.add(customer);
    }
}
