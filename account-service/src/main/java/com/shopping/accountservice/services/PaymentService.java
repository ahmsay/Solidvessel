package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Payment;
import com.shopping.accountservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private IAsyncRequestService requestService;

    public PaymentService(final IAsyncRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final long customerId) {
        Payment[] payments = requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/ofCustomer")
                .withQueryParameter("customerId", customerId)
                .withResponseType(Payment[].class)
                .send();
        return Arrays.asList(payments);
    }
}
