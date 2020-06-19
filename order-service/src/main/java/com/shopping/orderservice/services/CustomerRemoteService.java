package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private RestTemplate restTemplate;
    private IOrderService orderService;

    public CustomerRemoteService(final RestTemplate restTemplate, final IOrderService orderService) {
        this.restTemplate = restTemplate;
        this.orderService = orderService;
    }

    @Override
    public Customer getCustomerOfOrder(final String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        return restTemplate.getForObject(accountServiceUrl + "/customers/" + order.getCustomerId(), Customer.class);
    }
}
