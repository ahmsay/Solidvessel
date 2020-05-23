package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    private RestTemplate restTemplate;
    private ICustomerService customerService;

    public PaymentRemoteService(final RestTemplate restTemplate, final ICustomerService customerService) {
        this.restTemplate = restTemplate;
        this.customerService = customerService;
    }

    @Override
    public Payment[] getPaymentsOfCustomer(final String id) {
        String url = "http://localhost:8084/payments/ofCustomer/";
        Customer customer = customerService.getCustomerById(id);
        List<String> paymentIds = new ArrayList<>(customer.getPaymentIds());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("paymentIds", String.join(",", paymentIds));
        URI uri = builder.build().encode().toUri();

        ResponseEntity<Payment[]> response = restTemplate.getForEntity(uri, Payment[].class);
        return response.getBody();
    }
}
