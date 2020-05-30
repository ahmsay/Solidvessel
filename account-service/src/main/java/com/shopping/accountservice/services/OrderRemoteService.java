package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Order;
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
public class OrderRemoteService implements IOrderRemoteService {

    private RestTemplate restTemplate;
    private ICustomerService customerService;

    public OrderRemoteService(final RestTemplate restTemplate, final ICustomerService customerService) {
        this.restTemplate = restTemplate;
        this.customerService = customerService;
    }

    @Override
    public Set<Order> getOrdersOfCustomer(final String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            List<String> orderIds = new ArrayList<>(customer.getOrderIds());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/orders/withIds/")
                    .queryParam("orderIds", String.join(",", orderIds));
            URI uri = builder.build().encode().toUri();

            ResponseEntity<Set<Order>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            return response.getBody();
        }
        return null;
    }
}
