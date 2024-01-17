package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommand;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.test.contract.BaseProducerContractTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {PaymentController.class})
public class PaymentProducerContractTest extends BaseProducerContractTest {

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @MockBean
    private RealmResource keycloakRealm;

    @MockBean
    private CommandService<AcceptPaymentCommand> acceptPaymentCommandService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new PaymentController(paymentQueryPort, keycloakRealm, acceptPaymentCommandService));
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
                new Payment(1L, "123", products1, 150D, PaymentStatus.APPROVED),
                new Payment(2L, "123", products2, 1300D, PaymentStatus.APPROVED)
        );
    }

    private Payment createPayment() {
        var products = List.of(
                new Product(4L, "slippers", 12D, ProductCategory.CLOTHING, 2),
                new Product(5L, "chair", 50D, ProductCategory.FURNITURE, 3)
        );
        return new Payment(1L, "123", products, 174D, PaymentStatus.APPROVED);
    }
}
