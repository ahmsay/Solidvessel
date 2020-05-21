package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    private final RestTemplate restTemplate;

    public PaymentRemoteService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment[] getPaymentsOfCustomer(final int id) {
        String url = "http://localhost:8084/payments/ofCustomer/" + id;
        ResponseEntity<Payment[]> response = restTemplate.getForEntity(url, Payment[].class);
        return response.getBody();
    }
}
