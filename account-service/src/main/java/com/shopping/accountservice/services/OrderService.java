package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Order;
import com.shopping.accountservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private IAsyncRequestService requestService;

    public OrderService(final IAsyncRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<Order> getOrdersOfCustomer(final long customerId) {
        Order[] orders = requestService.createRequest(orderServiceUrl)
                .toPath("/orders/ofCustomer/" + customerId)
                .withResponseType(Order[].class)
                .send();
        return Arrays.asList(orders);
    }
}
