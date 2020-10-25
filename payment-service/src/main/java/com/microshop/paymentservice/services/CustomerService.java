package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Customer;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IPaymentService paymentService;
    private final IRemoteRequestService requestService;

    public CustomerService(final IPaymentService paymentService, final IRemoteRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public Customer findCustomerOfPayment(final Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment == null) {
            return null;
        }
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + payment.getCustomerId())
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Customer.class)
                .send();
    }
}
