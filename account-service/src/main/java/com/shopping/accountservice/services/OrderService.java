package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
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

    private ICustomerService customerService;
    private IAsyncRequestService requestService;

    public OrderService(final ICustomerService customerService, final IAsyncRequestService requestService) {
        this.customerService = customerService;
        this.requestService = requestService;
    }

    @Override
    public List<Order> getOrdersOfCustomer(final String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return null;
        }
        Order[] orders = requestService.createRequest(orderServiceUrl)
                .toPath("/orders/filter")
                .withQueryParameter("orderIds", String.join(",", customer.getOrderIds()))
                .withResponseType(Order[].class)
                .send();
        return Arrays.asList(orders);
    }
}
