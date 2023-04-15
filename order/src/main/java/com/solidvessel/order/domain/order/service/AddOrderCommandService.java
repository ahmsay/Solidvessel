package com.solidvessel.order.domain.order.service;

import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.port.OrderPort;
import com.solidvessel.order.domain.order.service.command.AddOrderCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddOrderCommandService implements CommandService<AddOrderCommand> {

    private final OrderPort orderPort;

    @Override
    public OperationResult execute(AddOrderCommand command) {
        orderPort.save(Order.newOrder(command.customerId(), command.paymentId()));
        return new OperationResult("Order is added.", ResultType.SUCCESS);
    }
}
