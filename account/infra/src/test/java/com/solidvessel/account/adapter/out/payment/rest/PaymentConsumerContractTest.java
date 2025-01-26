package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentStatus;
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
        ids = "solidvessel.payment:infra:+:stubs:8084",
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH
)
public class PaymentConsumerContractTest extends BaseConsumerContractTest {

    @Autowired
    private PaymentRestClient paymentRestClient;

    @Test
    void getPaymentsOfCustomer() {
        var payments = List.of(
                new PaymentResponse(1L, 150D, PaymentStatus.APPROVED, LocalDateTime.of(2025, Month.JANUARY, 26, 14, 16, 18, 575)),
                new PaymentResponse(2L, 1300D, PaymentStatus.APPROVED, LocalDateTime.of(2024, Month.AUGUST, 3, 9, 56, 42, 3815))
        );
        var response = paymentRestClient.getByCustomerId("123", "abc");
        assertEquals(payments, response);
    }
}
