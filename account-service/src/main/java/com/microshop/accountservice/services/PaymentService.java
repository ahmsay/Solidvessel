package com.microshop.accountservice.services;

import com.microshop.accountservice.configuration.remote.IRequestService;
import com.microshop.accountservice.configuration.remote.Services;
import com.microshop.accountservice.dto.PaymentDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IRequestService requestService;
    private final Services services;

    public PaymentService(final IRequestService requestService, final Services services) {
        this.requestService = requestService;
        this. services = services;
    }

    @Override
    public List<PaymentDTO> findByCustomerId(final Long customerId) {
        PaymentDTO[] payments = requestService.createRequest(services.getPayment())
                .toPath("/payments/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(PaymentDTO[].class)
                .send();
        return Arrays.asList(payments);
    }
}
