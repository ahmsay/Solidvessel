package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private IPaymentService paymentService;
    private IAsyncRequestService requestService;

    public CustomerRemoteService(final IPaymentService paymentService, final IAsyncRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public Customer getCustomerOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        return requestService.createRequest()
                .toUrl(accountServiceUrl + "/customers/" + payment.getCustomerId())
                .send();
    }
}
