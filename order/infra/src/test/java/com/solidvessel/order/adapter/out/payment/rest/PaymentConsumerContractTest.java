package com.solidvessel.order.adapter.out.payment.rest;

import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.contracttest.BaseConsumerContractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureStubRunner(
        ids = "solidvessel.payment:infra:+:stubs:8085",
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH
)
public class PaymentConsumerContractTest extends BaseConsumerContractTest {

    @Autowired
    private PaymentRestClient paymentRestClient;

    @Test
    void getPaymentOfOrder() {
        var payment = new PaymentResponse(1L, 174D);
        var response = paymentRestClient.getById(1L, "abc");
        assertEquals(payment, response);
    }
}
