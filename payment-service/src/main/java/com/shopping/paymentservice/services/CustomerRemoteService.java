package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    private RestTemplate restTemplate;

    public CustomerRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Customer getCustomerOfPayment(final String paymentId) {
        return restTemplate.getForObject("http://localhost:8081/customers/" + paymentId, Customer.class);
    }
}
