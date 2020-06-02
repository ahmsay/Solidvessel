package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;
import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderRemoteService implements IOrderRemoteService {

    private RestTemplate restTemplate;
    private IPaymentService paymentService;

    public OrderRemoteService(final RestTemplate restTemplate, final IPaymentService paymentService) {
        this.restTemplate = restTemplate;
        this.paymentService = paymentService;
    }

    @Override
    public Order getOrderOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return restTemplate.getForObject("http://order-service/orders/" + payment.getOrderId(), Order.class);
        }
        return null;
    }
}
