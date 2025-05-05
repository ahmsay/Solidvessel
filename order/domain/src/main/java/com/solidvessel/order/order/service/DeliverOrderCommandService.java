package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.DeliverOrderCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class DeliverOrderCommandService implements CommandService<DeliverOrderCommand, OperationResult> {

    private final OrderQueryPort orderQueryPort;
    private final OrderPort orderPort;

    @Override
    public OperationResult execute(DeliverOrderCommand command) {
        Order order = orderQueryPort.getById(command.id());
        if (!order.canDeliver()) {
            return new OperationResult("The order must be on it's way to deliver.", ResultType.ERROR);
        }
        order.deliver(command.recipient());
        orderPort.save(order);
        return new OperationResult("The order is delivered to %s".formatted(command.recipient()), ResultType.SUCCESS);
    }
}
