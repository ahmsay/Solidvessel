package com.microshop.accountservice.services;

import com.microshop.accountservice.configuration.remote.IRequestService;
import com.microshop.accountservice.configuration.remote.Services;
import com.microshop.accountservice.dto.OrderDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    private final IRequestService requestService;
    private final Services services;

    public OrderService(final IRequestService requestService, final Services services) {
        this.requestService = requestService;
        this. services = services;
    }

    @Override
    public List<OrderDTO> findByCustomerId(final Long customerId) {
        OrderDTO[] orders = requestService.createRequest(services.getOrder())
                .toPath("/orders/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(OrderDTO[].class)
                .send();
        return Arrays.asList(orders);
    }
}
