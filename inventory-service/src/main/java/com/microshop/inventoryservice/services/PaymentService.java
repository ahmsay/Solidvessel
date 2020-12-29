package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.remote.IRequestService;
import com.microshop.inventoryservice.wrapper.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private final IRequestService requestService;

    public PaymentService(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Payment findById(final Long id) {
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + id + "/pruned")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Payment.class)
                .send();
    }
}
