package com.microshop.paymentservice.services;

import com.microshop.paymentservice.remote.IRequestService;
import com.microshop.paymentservice.wrapper.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IRequestService requestService;

    public CustomerService(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Customer findById(final Long id) {
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + id)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Customer.class)
                .send();
    }
}
