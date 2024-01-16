package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommand;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.test.contract.BaseProducerContractTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {PaymentController.class})
public class PaymentProducerContractTest extends BaseProducerContractTest {

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @MockBean
    private CustomerQueryPort customerQueryPort;

    @MockBean
    private CommandService<AcceptPaymentCommand> acceptPaymentCommandService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new PaymentController(paymentQueryPort, customerQueryPort, acceptPaymentCommandService));
        when(paymentQueryPort.getByCustomerId("123")).thenReturn(createPayments());
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
                new Payment(1L, "123", products1, 150D),
                new Payment(2L, "123", products2, 1300D)
        );
    }
}
