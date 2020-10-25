package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Customer;
import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Value("${accountServiceUrl}")
    private String accountServiceUrl;

    private final IOrderService orderService;
    private final IRemoteRequestService requestService;

    public CustomerService(final IOrderService orderService, final IRemoteRequestService requestService) {
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @Override
    public Customer findCustomerOfOrder(final Long orderId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return null;
        }
        return requestService.createRequest(accountServiceUrl)
                .toPath("/customers/" + order.getCustomerId())
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Customer.class)
                .send();
    }
}
