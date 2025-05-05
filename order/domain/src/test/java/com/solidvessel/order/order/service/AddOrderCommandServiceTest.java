package com.solidvessel.order.order.service;

import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderPort;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class AddOrderCommandServiceTest extends BaseUnitTest {

    @Mock
    private OrderPort orderPort;

    @Test
    void addOrder() {
        var event = new PaymentApprovedEvent(1L, "123", "494 mexico city, mexico");
        var commandService = new AddOrderCommandService(orderPort);
        commandService.execute(event);
        verify(orderPort).save(new Order(OrderStatus.PREPARING, "123", 1L, "494 mexico city, mexico", null, null));
    }
}
