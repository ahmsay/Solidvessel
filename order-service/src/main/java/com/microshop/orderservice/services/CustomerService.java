package com.microshop.orderservice.services;

import com.microshop.orderservice.configuration.remote.IRequestService;
import com.microshop.orderservice.dto.CustomerDTO;
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
