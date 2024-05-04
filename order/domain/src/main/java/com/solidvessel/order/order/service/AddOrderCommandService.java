package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddOrderCommandService implements CommandService<PaymentApprovedEvent, Void> {

    private final OrderPort orderPort;

    @Override
    public Void execute(PaymentApprovedEvent event) {
        orderPort.create(Order.newOrder(event.customerId(), event.paymentId()));
        return null;
    }
}
