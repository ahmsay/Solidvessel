package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommandService;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
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

@WebMvcTest(controllers = {PaymentController.class})
public class PaymentProducerContractTest extends BaseProducerContractTest {

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @MockBean
    private KeycloakAdapter keycloakAdapter;

    @MockBean
    private AcceptPaymentCommandService acceptPaymentCommandService;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(new PaymentController(paymentQueryPort, keycloakAdapter, acceptPaymentCommandService));
        when(paymentQueryPort.getByCustomerId("123")).thenReturn(createPayments());
        when(paymentQueryPort.getById(1L)).thenReturn(createPayment());
    }

    private List<Payment> createPayments() {
        var products1 = List.of(
                new Product(3L, "table", 35D, ProductCategory.FURNITURE, 3),
                new Product(6L, "sickle", 9D, ProductCategory.TOOL, 5)
        );
        var products2 = List.of(
                new Product(2L, "macbook", 1200D, ProductCategory.ELECTRONICS, 1),
                new Product(8L, "shirt", 50D, ProductCategory.CLOTHING, 2)
        );
        return List.of(
                Payment.builder()
                        .id(1L)
                        .customerId("123")
                        .products(products1)
                        .totalPrice(150D)
                        .status(PaymentStatus.APPROVED)
                        .createdDate(LocalDateTime.of(2025, Month.JANUARY, 26, 14, 16, 18, 575))
                        .build(),
                Payment.builder()
                        .id(2L)
                        .customerId("123")
                        .products(products2)
                        .totalPrice(1300D)
                        .status(PaymentStatus.APPROVED)
                        .createdDate(LocalDateTime.of(2024, Month.AUGUST, 3, 9, 56, 42, 3815))
                        .build()
        );
    }

    private Payment createPayment() {
        var products = List.of(
                new Product(4L, "slippers", 12D, ProductCategory.CLOTHING, 2),
                new Product(5L, "chair", 50D, ProductCategory.FURNITURE, 3)
        );
        return Payment.builder().id(1L).customerId("123").products(products).totalPrice(174D).status(PaymentStatus.APPROVED).build();
    }
}
