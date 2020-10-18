package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IOrderService orderService;
    private final IAsyncRequestService requestService;

    public CustomerService(final IOrderService orderService, final IAsyncRequestService requestService) {
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @Override
    public Customer getCustomerOfOrder(final Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + order.getCustomerId())
                .withResponseType(Customer.class)
                .send();
    }
}
