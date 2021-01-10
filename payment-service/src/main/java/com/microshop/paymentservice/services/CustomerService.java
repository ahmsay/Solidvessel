package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;
import com.microshop.paymentservice.remote.IRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IRequestService requestService;

    public CustomerService(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CustomerDTO findById(final Long id) {
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + id + "/pruned")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(CustomerDTO.class)
                .send();
    }
}
