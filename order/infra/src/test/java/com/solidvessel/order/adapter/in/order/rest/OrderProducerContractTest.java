package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.shared.test.contract.BaseProducerContractTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {OrderController.class})
public class OrderProducerContractTest extends BaseProducerContractTest {

    @MockBean
    private OrderQueryPort orderQueryPort;

    @MockBean
    private RealmResource keycloakRealm;

    @MockBean
    private PaymentRestClient paymentRestClient;

    @BeforeEach
    public void setup() {
        var orders = List.of(
                new Order(1L, OrderStatus.DELIVERED, "123", 5L),
                new Order(2L, OrderStatus.ON_THE_WAY, "123", 6L)
        );
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderQueryPort, keycloakRealm, paymentRestClient));
        when(orderQueryPort.getByCustomerId("123")).thenReturn(orders);
    }
}
