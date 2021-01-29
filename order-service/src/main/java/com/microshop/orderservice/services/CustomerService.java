package com.microshop.orderservice.services;

import com.microshop.orderservice.configuration.remote.IRequestService;
import com.microshop.orderservice.configuration.remote.Services;
import com.microshop.orderservice.dto.CustomerDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final IRequestService requestService;
    private final Services services;

    public CustomerService(final IRequestService requestService, final Services services) {
        this.requestService = requestService;
        this. services = services;
    }

    @Override
    public CustomerDTO findById(final Long id) {
        return requestService.createRequest(services.getAccount())
                .toPath("/customers/" + id + "/pruned")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(CustomerDTO.class)
                .send();
    }
}
