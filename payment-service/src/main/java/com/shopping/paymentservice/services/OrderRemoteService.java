package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderRemoteService implements IOrderRemoteService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private IPaymentService paymentService;
    private IAsyncRequestService requestService;

    public OrderRemoteService(final IPaymentService paymentService, final IAsyncRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public Order getOrderOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        return requestService.createRequest()
                .toUrl(orderServiceUrl + "/orders/" + payment.getOrderId())
                .send();
    }
}
