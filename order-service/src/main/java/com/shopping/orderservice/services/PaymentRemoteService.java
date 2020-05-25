package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    private RestTemplate restTemplate;

    public PaymentRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment getPaymentOfOrder(final String orderId) {
        return restTemplate.getForObject("http://localhost:8084/payments/" + orderId, Payment.class);
    }
}
