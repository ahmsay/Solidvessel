package com.microshop.accountservice.services;

import com.microshop.accountservice.configuration.remote.IRequestService;
import com.microshop.accountservice.configuration.remote.URLs;
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
    private final URLs urls;

    public OrderService(final IRequestService requestService, final URLs urls) {
        this.requestService = requestService;
        this. urls = urls;
    }

    @Override
    public List<OrderDTO> findByCustomerId(final Long customerId) {
        OrderDTO[] orders = requestService.createRequest(urls.getOrder())
                .toPath("/orders/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(OrderDTO[].class)
                .send();
        return Arrays.asList(orders);
    }
}
