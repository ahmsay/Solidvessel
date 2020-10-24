package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IPaymentService paymentService;
    private final IAsyncRequestService requestService;

    public CustomerService(final IPaymentService paymentService, final IAsyncRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public Customer getCustomerOfPayment(final Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + payment.getCustomerId())
                .withResponseType(Customer.class)
                .send();
    }
}
