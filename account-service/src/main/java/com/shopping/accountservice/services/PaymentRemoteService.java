package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Payment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    public List<Payment> getPaymentsOfCustomer(final String id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            List<String> paymentIds = new ArrayList<>(customer.getPaymentIds());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8084/payments/withIds/")
                    .queryParam("paymentIds", String.join(",", paymentIds));
            URI uri = builder.build().encode().toUri();

            ResponseEntity<List<Payment>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>() {});
            return response.getBody();
        }
        return null;
    }
}
