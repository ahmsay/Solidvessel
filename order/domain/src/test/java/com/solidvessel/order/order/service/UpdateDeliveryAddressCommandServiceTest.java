package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.UpdateDeliveryAddressCommand;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateDeliveryAddressCommandServiceTest extends BaseUnitTest {

    @Mock
    private OrderQueryPort orderQueryPort;

    @Mock
    private OrderPort orderPort;

    @Test
    void updateDeliveryAddress() {
        var command = new UpdateDeliveryAddressCommand(1L, "123", "Texas");
        var commandService = new UpdateDeliveryAddressCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").address("Yuma").status(OrderStatus.PREPARING).build();
        when(orderQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(order);
        var operationResult = commandService.execute(command);
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertEquals("Texas", order.getAddress());
        verify(orderPort).save((order));
    }

    @Test
    void orderMustBeInPreparation() {
        var command = new UpdateDeliveryAddressCommand(1L, "123", "Texas");
        var commandService = new UpdateDeliveryAddressCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").address("Yuma").status(OrderStatus.ON_THE_WAY).build();
        when(orderQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(order);
        var operationResult = commandService.execute(command);
        assertEquals(ResultType.ERROR, operationResult.resultType());
        assertEquals("Your order must be in preparation to change the delivery address.", operationResult.message());
        assertEquals("Yuma", order.getAddress());
        verifyNoInteractions(orderPort);
    }
}
