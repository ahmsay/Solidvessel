package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    @Value("${paymentServiceUrl:failed fetching url}")
    private String paymentServiceUrl;

    private RestTemplate restTemplate;
    private ICustomerService customerService;

    public PaymentRemoteService(final RestTemplate restTemplate, final ICustomerService customerService) {
        this.restTemplate = restTemplate;
        this.customerService = customerService;
    }

    @Override
    public Set<Payment> getPaymentsOfCustomer(final String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            List<String> paymentIds = new ArrayList<>(customer.getPaymentIds());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(paymentServiceUrl + "payments/withIds/")
                    .queryParam("paymentIds", String.join(",", paymentIds));
            URI uri = builder.build().encode().toUri();

            ResponseEntity<Set<Payment>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            return response.getBody();
        }
        return null;
    }
}
