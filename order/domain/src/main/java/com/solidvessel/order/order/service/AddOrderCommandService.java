package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.service.command.AddOrderCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddOrderCommandService implements CommandService<AddOrderCommand> {

    private final OrderPort orderPort;

    @Override
    public OperationResult execute(AddOrderCommand command) {
        orderPort.save(Order.newOrder(command.customerId(), command.paymentId()));
        return new OperationResult("Order is added.", ResultType.SUCCESS);
    }
}