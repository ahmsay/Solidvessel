package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.VoidCommandService;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddOrderCommandService implements VoidCommandService<PaymentApprovedEvent> {

    private final OrderPort orderPort;

    @Override
    public void execute(PaymentApprovedEvent event) {
        orderPort.save(Order.newOrder(event.customerId(), event.paymentId(), event.address()));
    }
}
