package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.OrderDTO;
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
    public List<OrderDTO> findByCustomerId(final Long customerId) {
        OrderDTO[] orders = requestService.createRequest(orderServiceUrl)
                .toPath("/orders/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(OrderDTO[].class)
                .send();
        return Arrays.asList(orders);
    }
}
