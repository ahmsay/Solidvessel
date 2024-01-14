package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.contracttest.BaseConsumerContractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

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
    public void getOrdersOfCustomers() {
        var orders = List.of(new OrderResponse(1L, "DELIVERED", 5L), new OrderResponse(2L, "ON_THE_WAY", 6L));
        var response = orderRestClient.getByCustomerId("123", "abc");
        assertEquals(orders, response);
    }
}
