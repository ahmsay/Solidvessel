package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.CancelOrderCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CancelOrderCommandService implements CommandService<CancelOrderCommand, OperationResult> {

    private final OrderQueryPort orderQueryPort;
    private final OrderPort orderPort;

    @Override
    public OperationResult execute(CancelOrderCommand command) {
        Order order = orderQueryPort.getByIdAndCustomerId(command.id(), command.customerId());
        if (!order.canCancel()) {
            return new OperationResult("Your order must either being prepared or on it's way to cancel.", ResultType.ERROR);
        }
        order.cancel(command.cancellationReason(), command.explanation());
        orderPort.save(order);
        return new OperationResult("Your order is cancelled.", ResultType.SUCCESS);
    }
}
