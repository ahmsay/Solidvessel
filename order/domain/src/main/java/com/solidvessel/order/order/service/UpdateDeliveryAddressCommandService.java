package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.UpdateDeliveryAddressCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdateDeliveryAddressCommandService implements CommandService<UpdateDeliveryAddressCommand, OperationResult> {

    private final OrderQueryPort orderQueryPort;
    private final OrderPort orderPort;

    @Override
    public OperationResult execute(UpdateDeliveryAddressCommand command) {
        Order order = orderQueryPort.getByIdAndCustomerId(command.id(), command.customerId());
        if (!order.canUpdateAddress()) {
            return new OperationResult("Your order must be in preparation to change the delivery address.", ResultType.ERROR);
        }
        order.updateAddress(command.address());
        orderPort.save(order);
        return new OperationResult("Your delivery address is updated successfully. New address: %s".formatted(command.address()), ResultType.SUCCESS);
    }
}
