package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.CancelOrderCommandService;
import com.solidvessel.order.order.service.DeliverOrderCommandService;
import com.solidvessel.order.order.service.UpdateDeliveryAddressCommandService;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.test.contract.BaseProducerContractTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {OrderController.class})
public class OrderProducerContractTest extends BaseProducerContractTest {

    @MockBean
    private OrderQueryPort orderQueryPort;

    @MockBean
    private KeycloakAdapter keycloakAdapter;

    @MockBean
    private PaymentRestClient paymentRestClient;

    @MockBean
    private CancelOrderCommandService cancelOrderCommandService;

    @MockBean
    private DeliverOrderCommandService deliverOrderCommandService;

    @MockBean
    private UpdateDeliveryAddressCommandService updateDeliveryAddressCommandService;

    @BeforeEach
    void setup() {
        var orders = List.of(
                Order.builder()
                        .id(1L)
                        .status(OrderStatus.DELIVERED)
                        .customerId("123")
                        .paymentId(5L)
                        .address("26593-birmingham,-uk")
                        .createdDate(LocalDateTime.of(2025, Month.MARCH, 13, 22, 45, 3, 4831))
                        .recipient("Judge-Holden")
                        .build(),
                Order.builder()
                        .id(2L)
                        .status(OrderStatus.ON_THE_WAY)
                        .customerId("123")
                        .paymentId(6L)
                        .address("48249-helsinki,-finland")
                        .createdDate(LocalDateTime.of(2023, Month.DECEMBER, 9, 11, 49, 32, 8371))
                        .build()
        );
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderQueryPort, keycloakAdapter, paymentRestClient, cancelOrderCommandService, deliverOrderCommandService, updateDeliveryAddressCommandService));
        when(orderQueryPort.getByCustomerId("123")).thenReturn(orders);
    }
}
