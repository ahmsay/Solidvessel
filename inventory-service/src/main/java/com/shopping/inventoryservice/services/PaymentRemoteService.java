package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    private RestTemplate restTemplate;

    public PaymentRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment getPaymentOfProduct(final String id) {
        return restTemplate.getForObject("http://localhost:8084/payments/" + id, Payment.class);
    }
}
