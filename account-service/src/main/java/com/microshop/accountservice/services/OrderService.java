package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Order;
import com.microshop.accountservice.remote.IRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private final IRequestService requestService;

    public OrderService(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<Order> findByCustomerId(final Long customerId) {
        Order[] orders = requestService.createRequest(orderServiceUrl)
                .toPath("/orders/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Order[].class)
                .send();
        return Arrays.asList(orders);
    }
}
