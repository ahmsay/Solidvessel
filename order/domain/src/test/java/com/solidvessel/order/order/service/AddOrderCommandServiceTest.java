package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.order.service.command.AddOrderCommand;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddOrderCommandServiceTest {

    @Mock
    private OrderPort orderPort;

    @Test
    void addOrder() {
        var command = new AddOrderCommand("123", 1L);
        var commandService = new AddOrderCommandService(orderPort);
        var operationResult = commandService.execute(command);
        verify(orderPort).save(any(Order.class));
        assertEquals(operationResult.resultType(), ResultType.SUCCESS);
    }
}
