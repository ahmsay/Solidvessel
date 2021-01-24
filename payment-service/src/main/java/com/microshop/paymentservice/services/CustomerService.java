package com.microshop.paymentservice.services;

import com.microshop.paymentservice.configuration.remote.IRequestService;
import com.microshop.paymentservice.configuration.remote.URLs;
import com.microshop.paymentservice.dto.CustomerDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final IRequestService requestService;
    private final URLs urls;

    public CustomerService(final IRequestService requestService, final URLs urls) {
        this.requestService = requestService;
        this. urls = urls;
    }

    @Override
    public CustomerDTO findById(final Long id) {
        return requestService.createRequest(urls.getAccount())
                .toPath("/customers/" + id + "/pruned")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(CustomerDTO.class)
                .send();
    }
}
