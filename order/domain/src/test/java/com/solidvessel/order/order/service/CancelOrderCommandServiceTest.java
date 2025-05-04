package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.CancellationReason;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.command.CancelOrderCommand;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CancelOrderCommandServiceTest extends BaseUnitTest {

    @Mock
    private OrderQueryPort orderQueryPort;

    @Mock
    private OrderPort orderPort;

    @Test
    void cancelOrder() {
        var command = new CancelOrderCommand(1L, "123", CancellationReason.DELIVERY_TOOK_TOO_LONG, "When are you going to deliver this, next year??");
        var commandService = new CancelOrderCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").status(OrderStatus.ON_THE_WAY).build();
        when(orderQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(order);
        var operationResult = commandService.execute(command);
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertNotNull(order.getCancellation());
        verify(orderPort).save((order));
    }

    @Test
    void deliveredOrderCannotBeCancelled() {
        var command = new CancelOrderCommand(1L, "123", CancellationReason.DONT_NEED_ANYMORE, "no need");
        var commandService = new CancelOrderCommandService(orderQueryPort, orderPort);
        var order = Order.builder().id(1L).customerId("123").status(OrderStatus.DELIVERED).build();
        when(orderQueryPort.getByIdAndCustomerId(1L, "123")).thenReturn(order);
        var operationResult = commandService.execute(command);
        assertEquals(ResultType.ERROR, operationResult.resultType());
        assertEquals("Your order must either being prepared or on it's way to cancel.", operationResult.message());
        assertNull(order.getCancellation());
        verifyNoInteractions(orderPort);
    }
}
