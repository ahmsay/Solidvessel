package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.adapter.in.payment.rest.request.AcceptPaymentRequest;
import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentDetailResponse;
import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentResponse;
import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommandService;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockCustomer;
import com.solidvessel.shared.test.controller.WithMockManager;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {PaymentController.class})
public class PaymentControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @MockBean
    private KeycloakAdapter keycloakAdapter;

    @MockBean
    private AcceptPaymentCommandService acceptPaymentCommandService;

    @Test
    @WithMockManager
    void getPayments() throws Exception {
        var queryOptions = new QueryOptions(0);
        var products = List.of(new Product(1L, "table", 35D, ProductCategory.FURNITURE, 3));
        var payments = List.of(new Payment("123", products, 105D, PaymentStatus.APPROVED));
        when(paymentQueryPort.getPayments(queryOptions)).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/")
                        .param("pageNumber", "0")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payments.stream().map(PaymentResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void getPaymentsOfCurrentCustomer() throws Exception {
        var products = List.of(new Product(1L, "table", 35D, ProductCategory.FURNITURE, 3));
        var payments = List.of(new Payment("123", products, 105D, PaymentStatus.APPROVED));
        when(paymentQueryPort.getByCustomerId(SessionUtil.getCurrentUserId())).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/ofCurrentCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payments.stream().map(PaymentResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    void getPaymentById() throws Exception {
        var products = List.of(new Product(1L, "table", 35D, ProductCategory.FURNITURE, 3));
        var payment = Payment.builder().id(1L).customerId("123").products(products).totalPrice(105D).status(PaymentStatus.CANCELLED).build();
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        MvcResult mvcResult = mockMvc.perform(
                get("/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(PaymentResponse.from(payment)), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    void getPaymentDetailById() throws Exception {
        var products = List.of(new Product(1L, "table", 35D, ProductCategory.FURNITURE, 3));
        var payment = Payment.builder().id(1L).customerId("123").products(products).totalPrice(105D).status(PaymentStatus.APPROVED).build();
        var customer = new CustomerResponse("123", "lorne", "malvo");
        var paymentDetail = PaymentDetailResponse.from(payment, customer);
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        when(keycloakAdapter.getUser("123")).thenReturn(createUser());
        MvcResult mvcResult = mockMvc.perform(
                get("/1/detail")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(paymentDetail), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    void getPaymentsByCustomerId() throws Exception {
        var products = List.of(new Product(1L, "table", 35D, ProductCategory.FURNITURE, 3));
        var payments = List.of(new Payment("123", products, 105D, PaymentStatus.APPROVED));
        when(paymentQueryPort.getByCustomerId("123")).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/ofCustomer/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payments.stream().map(PaymentResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void acceptPayment() throws Exception {
        var request = new AcceptPaymentRequest();
        when(acceptPaymentCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/accept")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    private UserRepresentation createUser() {
        var user = new UserRepresentation();
        user.setId("123");
        user.setFirstName("lorne");
        user.setLastName("malvo");
        user.setEmail("lorne@mail.com");
        var birthDate = List.of(LocalDate.of(1980, 4, 23).toString());
        var phoneNumber = List.of("+90475274");
        user.setAttributes(Map.of("birthDate", birthDate, "phoneNumber", phoneNumber));
        return user;
    }
}
