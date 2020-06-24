package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
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

    private ICustomerService customerService;
    private IAsyncRequestService requestService;

    public PaymentService(final ICustomerService customerService, final IAsyncRequestService requestService) {
        this.customerService = customerService;
        this.requestService = requestService;
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return null;
        }
        Payment[] payments = requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/filter")
                .withQueryParameter("paymentIds", String.join(",", customer.getPaymentIds()))
                .withResponseType(Payment[].class)
                .send();
        return Arrays.asList(payments);
    }
}
