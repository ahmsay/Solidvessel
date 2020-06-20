package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Order;
import com.shopping.accountservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRemoteService implements IOrderRemoteService {

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    private ICustomerService customerService;
    private IAsyncRequestService requestService;

    public OrderRemoteService(final ICustomerService customerService, final IAsyncRequestService requestService) {
        this.customerService = customerService;
        this.requestService = requestService;
    }

    @Override
    public List<Order> getOrdersOfCustomer(final String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return null;
        }
        return requestService.createRequest()
                .toUrl(orderServiceUrl + "/orders/withIds/")
                .withQueryParam("orderIds", String.join(",", customer.getOrderIds()))
                .send();
    }
}
