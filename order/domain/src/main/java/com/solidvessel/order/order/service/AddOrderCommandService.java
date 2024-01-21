package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddOrderCommandService implements CommandService<PaymentApprovedEvent> {

    private final OrderPort orderPort;

    @Override
    public OperationResult execute(PaymentApprovedEvent event) {
        orderPort.save(Order.newOrder(event.customerId(), event.paymentId()));
        return new OperationResult("Order is added.", ResultType.SUCCESS);
    }
}
