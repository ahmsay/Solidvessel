package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.DeliverOrderCommand;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeliverOrderCommandServiceTest extends BaseUnitTest {

    @Mock
    private OrderQueryPort orderQueryPort;

    @Mock
    private OrderPort orderPort;

    @Test
    void deliverOrder() {
        var command = new DeliverOrderCommand(1L, "John Joel Glanton");
        var commandService = new DeliverOrderCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").status(OrderStatus.ON_THE_WAY).build();
        when(orderQueryPort.getById(1L)).thenReturn(order);
        var operationResult = commandService.execute(command);
        verify(orderPort).save((order));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void notOnTheWayOrderCannotBeDelivered() {
        var command = new DeliverOrderCommand(1L, "John Joel Glanton");
        var commandService = new DeliverOrderCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").status(OrderStatus.PREPARING).build();
        when(orderQueryPort.getById(1L)).thenReturn(order);
        var operationResult = commandService.execute(command);
        verifyNoInteractions(orderPort);
        assertEquals(ResultType.ERROR, operationResult.resultType());
        assertEquals("The order must be on it's way to deliver.", operationResult.message());
        assertEquals(OrderStatus.PREPARING, order.getStatus());
    }
}
