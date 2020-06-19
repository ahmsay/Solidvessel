package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private RestTemplate restTemplate;
    private IOrderService orderService;

    public PaymentRemoteService(final RestTemplate restTemplate, final IOrderService orderService) {
        this.restTemplate = restTemplate;
        this.orderService = orderService;
    }

    @Override
    public Payment getPaymentOfOrder(final String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        return restTemplate.getForObject(paymentServiceUrl + "/payments/" + order.getPaymentId(), Payment.class);
    }
}
