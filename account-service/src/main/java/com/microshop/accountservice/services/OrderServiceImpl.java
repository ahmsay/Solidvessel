package com.microshop.accountservice.services;

import com.microshop.accountservice.configuration.remote.IRequestService;
import com.microshop.accountservice.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private final IRequestService requestService;

    public OrderServiceImpl(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<OrderDTO> findByCustomerId(final Long customerId) {
        OrderDTO[] orders = requestService.createRequest(orderServiceUrl)
                .toPath("/orders/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(OrderDTO[].class)
                .send();
        return Arrays.asList(orders);
    }
}
