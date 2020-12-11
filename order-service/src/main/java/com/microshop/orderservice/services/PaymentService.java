package com.microshop.orderservice.services;

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

    private final IRemoteRequestService requestService;

    public PaymentService(final IRemoteRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Payment findById(final Long id) {
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + id)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Payment.class)
                .send();
    }
}
