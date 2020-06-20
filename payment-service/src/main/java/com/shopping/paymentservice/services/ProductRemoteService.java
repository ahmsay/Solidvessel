package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class ProductRemoteService implements IProductRemoteService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private RestTemplate restTemplate;
    private IPaymentService paymentService;

    public ProductRemoteService(final IPaymentService paymentService, final RestTemplate restTemplate) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProductsOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(inventoryServiceUrl + "/products/withIds/").queryParam("productIds", String.join(",", payment.getProductIds()));
        URI uri = builder.build().encode().toUri();
        ResponseEntity<List<Product>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
