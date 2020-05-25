package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderRemoteService implements IOrderRemoteService {

    private RestTemplate restTemplate;

    public OrderRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Order getOrderOfPayment(final String paymentId) {
        return restTemplate.getForObject("http://localhost:8083/orders/" + paymentId, Order.class);
    }
}
