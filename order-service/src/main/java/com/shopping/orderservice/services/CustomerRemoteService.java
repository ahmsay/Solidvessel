package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private IOrderService orderService;
    private IAsyncRequestService requestService;

    public CustomerRemoteService(final IOrderService orderService, final IAsyncRequestService requestService) {
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @Override
    public Customer getCustomerOfOrder(final String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        return requestService.createRequest()
                .toUrl(accountServiceUrl + "/customers/" + order.getCustomerId())
                .send();
    }
}
