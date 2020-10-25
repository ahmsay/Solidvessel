package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.entity.Payment;
import com.microshop.orderservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private final IOrderService orderService;
    private final IRemoteRequestService requestService;

    public PaymentService(final IOrderService orderService, final IRemoteRequestService requestService) {
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @Override
    public Payment findPaymentOfOrder(final Long orderId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return null;
        }
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + order.getPaymentId())
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Payment.class)
                .send();
    }
}
