package com.solidvessel.order.domain.order.service;

import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderPort orderPort;

    public void add(final Order order) {
        orderPort.add(order);
    }
}
