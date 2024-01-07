package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.customer.port.CustomerQueryPort;
import com.solidvessel.account.order.model.Order;
import com.solidvessel.account.order.port.OrderQueryPort;
import com.solidvessel.account.payment.model.Payment;
import com.solidvessel.account.payment.port.PaymentQueryPort;
import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CustomerController.class})
public class CustomerControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerQueryPort customerQueryPort;

    @MockBean
    private OrderQueryPort orderQueryPort;

    @MockBean
    private PaymentQueryPort paymentQueryPort;

    @Test
    @WithMockManager
    public void getAllCustomers() throws Exception {
        var customers = List.of(new Customer("123", "lorne", "malvo", LocalDate.of(1980, 4, 23), "lorne@mail.com", "+90475274"));
        when(customerQueryPort.getAll()).thenReturn(customers);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(customers.stream().map(CustomerResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getCustomerById() throws Exception {
        var customer = new Customer("123", "lorne", "malvo", LocalDate.of(1980, 4, 23), "lorne@mail.com", "+90475274");
        when(customerQueryPort.getById("123")).thenReturn(customer);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(CustomerResponse.from(customer)), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getCustomerDetailById() throws Exception {
        var customer = new Customer("123", "lorne", "malvo", LocalDate.of(1980, 4, 23), "lorne@mail.com", "+90475274");
        var orders = List.of(new Order(1L, "DELIVERED", 5L));
        var payments = List.of(new Payment(5L, 260D));
        var customerDetail = CustomerDetailResponse.from(customer, orders, payments);
        when(customerQueryPort.getById("123")).thenReturn(customer);
        when(orderQueryPort.getOrdersOfCustomer("123")).thenReturn(orders);
        when(paymentQueryPort.getPaymentsOfCustomer("123")).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer/123/detail")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(customerDetail), bodyOf(mvcResult));
    }
}
