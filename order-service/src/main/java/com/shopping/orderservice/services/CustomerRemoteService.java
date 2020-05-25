package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    private RestTemplate restTemplate;

    public CustomerRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Customer getCustomerOfOrder(final String orderId) {
        return restTemplate.getForObject("http://localhost:8081/customers/" + orderId, Customer.class);
    }
}
