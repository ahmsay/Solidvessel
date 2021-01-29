package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.configuration.remote.IRequestService;
import com.microshop.inventoryservice.configuration.remote.Services;
import com.microshop.inventoryservice.dto.PaymentDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PaymentDTO findById(final Long id) {
        return requestService.createRequest(services.getPayment())
                .toPath("/payments/" + id + "/pruned")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(PaymentDTO.class)
                .send();
    }
}
