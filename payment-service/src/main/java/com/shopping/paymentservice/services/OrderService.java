package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private IPaymentService paymentService;
    private IAsyncRequestService requestService;

    public OrderService(final IPaymentService paymentService, final IAsyncRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public Order getOrderOfPayment(final long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        return requestService.createRequest(orderServiceUrl)
                .toPath("/orders/" + payment.getOrderId())
                .withResponseType(Order.class)
                .send();
    }
}
