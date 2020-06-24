package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.entity.Payment;
import com.shopping.orderservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private IOrderService orderService;
    private IAsyncRequestService requestService;

    public PaymentService(final IOrderService orderService, final IAsyncRequestService requestService) {
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @Override
    public Payment getPaymentOfOrder(final String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + order.getPaymentId())
                .withResponseType(Payment.class)
                .send();
    }
}
