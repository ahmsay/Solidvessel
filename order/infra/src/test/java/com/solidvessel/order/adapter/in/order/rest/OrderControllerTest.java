package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.in.order.rest.response.OrderDetailResponse;
import com.solidvessel.order.adapter.in.order.rest.response.OrderResponse;
import com.solidvessel.order.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.customer.model.Customer;
import com.solidvessel.order.customer.port.CustomerQueryPort;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.model.OrderStatus;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.shared.test.controller.BaseControllerTest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrderController.class})
public class OrderControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderQueryPort orderQueryPort;

    @MockBean
    private CustomerQueryPort customerQueryPort;

    @MockBean
    private PaymentRestClient paymentRestClient;

    @Test
    @WithMockManager
    public void getAllOrders() throws Exception {
        var orders = List.of(new Order(1L, OrderStatus.DELIVERED, "123", 1L));
        when(orderQueryPort.getAll()).thenReturn(orders);
        MvcResult mvcResult = mockMvc.perform(
                get("/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(orders.stream().map(OrderResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getOrderById() throws Exception {
        var order = new Order(1L, OrderStatus.DELIVERED, "123", 1L);
        when(orderQueryPort.getById(1L)).thenReturn(order);
        MvcResult mvcResult = mockMvc.perform(
                get("/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OrderResponse.from(order)), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getOrderDetailById() throws Exception {
        var order = new Order(1L, OrderStatus.DELIVERED, "123", 1L);
        var customer = new Customer("123", "lorne", "malvo");
        var payment = new PaymentResponse(1L, 105D);
        var orderDetail = OrderDetailResponse.from(order, customer, payment);
        when(orderQueryPort.getById(1L)).thenReturn(order);
        when(customerQueryPort.getCustomerOfOrder("123")).thenReturn(customer);
        when(paymentRestClient.getById(1L, "abc")).thenReturn(payment);
        MvcResult mvcResult = mockMvc.perform(
                get("/1/detail")
                        .header("authorization", "abc")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(orderDetail), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getOrderByCustomerId() throws Exception {
        var orders = List.of(new Order(1L, OrderStatus.DELIVERED, "123", 1L));
        when(orderQueryPort.getByCustomerId("123")).thenReturn(orders);
        MvcResult mvcResult = mockMvc.perform(
                get("/ofCustomer/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(orders.stream().map(OrderResponse::from).toList()), bodyOf(mvcResult));
    }
}
