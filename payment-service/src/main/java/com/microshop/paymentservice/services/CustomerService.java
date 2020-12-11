package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Customer;
import com.microshop.paymentservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IRemoteRequestService requestService;

    public CustomerService(final IRemoteRequestService requestService) {
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
