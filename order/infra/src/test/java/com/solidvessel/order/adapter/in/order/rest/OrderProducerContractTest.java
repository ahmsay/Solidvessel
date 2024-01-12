package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.customer.port.CustomerQueryPort;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.payment.port.PaymentQueryPort;
import com.solidvessel.shared.Profiles;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest
@ActiveProfiles(Profiles.INTEGRATION_TEST)
public class OrderProducerContractTest {

    @MockBean
    private OrderQueryPort orderQueryPort;

    @MockBean
    private CustomerQueryPort customerQueryPort;

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @BeforeEach
    public void setup() {
        var orders = List.of(
                new Order(1L, OrderStatus.DELIVERED, "123", 5L),
                new Order(2L, OrderStatus.ON_THE_WAY, "123", 6L)
        );
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderQueryPort, customerQueryPort, paymentQueryPort));
        when(orderQueryPort.getByCustomerId("123")).thenReturn(orders);
    }
}
