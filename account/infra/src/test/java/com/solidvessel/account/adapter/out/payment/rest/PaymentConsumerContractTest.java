package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.account.contracttest.BaseConsumerContractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureStubRunner(
        ids = "solidvessel.payment:infra:+:stubs:8084",
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH
)
public class PaymentConsumerContractTest extends BaseConsumerContractTest {

    @Autowired
    private PaymentRestClient paymentRestClient;

    @Test
    public void getPaymentsOfCustomer() {
        var payments = List.of(new PaymentResponse(1L, 150D), new PaymentResponse(2L, 1300D));
        var response = paymentRestClient.getByCustomerId("123", "abc");
        assertEquals(payments, response);
    }
}
