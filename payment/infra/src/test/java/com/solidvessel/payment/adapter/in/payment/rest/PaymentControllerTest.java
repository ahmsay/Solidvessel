package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.adapter.in.payment.rest.request.AcceptPaymentRequest;
import com.solidvessel.payment.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.payment.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommandService;
import com.solidvessel.payment.product.datamodel.ProductDataModel;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockCustomer;
import com.solidvessel.shared.test.controller.WithMockManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

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
    private CustomerQueryPort customerQueryPort;

    @MockBean
    private AcceptPaymentCommandService acceptPaymentCommandService;

    @Test
    @WithMockManager
    public void getAllPayments() throws Exception {
        var products = List.of(new ProductDataModel(1L, 3, "table", 35D));
        var payments = List.of(new PaymentDataModel(1L, "123", products, 105D));
        when(paymentQueryPort.getAll()).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payments), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getPaymentById() throws Exception {
        var products = List.of(new ProductDataModel(1L, 3, "table", 35D));
        var payment = new PaymentDataModel(1L, "123", products, 105D);
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        MvcResult mvcResult = mockMvc.perform(
                get("/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payment), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getPaymentDetailById() throws Exception {
        var products = List.of(new ProductDataModel(1L, 3, "table", 35D));
        var payment = new PaymentDataModel(1L, "123", products, 105D);
        var customer = new CustomerDataModel("123", "lorne", "malvo");
        var paymentDetail = PaymentDetailDataModel.from(payment, customer);
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        when(customerQueryPort.getCustomerOfPayment("123")).thenReturn(customer);
        MvcResult mvcResult = mockMvc.perform(
                get("/1/detail")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(paymentDetail), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getPaymentByCustomerId() throws Exception {
        var products = List.of(new ProductDataModel(1L, 3, "table", 35D));
        var payments = List.of(new PaymentDataModel(1L, "123", products, 105D));
        when(paymentQueryPort.getByCustomerId("123")).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/ofCustomer/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(payments), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void acceptPayment() throws Exception {
        var request = new AcceptPaymentRequest();
        when(acceptPaymentCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/accept")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
