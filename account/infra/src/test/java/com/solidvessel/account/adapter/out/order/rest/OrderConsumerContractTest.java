package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.order.rest.response.OrderStatus;
import com.solidvessel.account.contracttest.BaseConsumerContractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureStubRunner(
        ids = "solidvessel.order:infra:+:stubs:8083",
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH
)
public class OrderConsumerContractTest extends BaseConsumerContractTest {

    @Autowired
    private OrderRestClient orderRestClient;

    @Test
    void getOrdersOfCustomers() {
        var orders = List.of(
                new OrderResponse(1L, OrderStatus.DELIVERED, 5L, "26593-birmingham,-uk", LocalDateTime.of(2025, Month.MARCH, 13, 22, 45, 3, 4831), null, "Judge-Holden"),
                new OrderResponse(2L, OrderStatus.ON_THE_WAY, 6L, "48249-helsinki,-finland", LocalDateTime.of(2023, Month.DECEMBER, 9, 11, 49, 32, 8371), null, null)
        );
        var response = orderRestClient.getByCustomerId("123", "abc");
        assertEquals(orders, response);
    }
}
