package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.adapter.out.order.rest.OrderRestClient;
import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.account.order.model.OrderStatus;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.test.controller.BaseControllerTest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CustomerController.class})
public class CustomerControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeycloakAdapter keycloakAdapter;

    @MockBean
    private OrderRestClient orderRestClient;

    @MockBean
    private PaymentRestClient paymentRestClient;

    @Test
    @WithMockManager
    public void getCustomers() throws Exception {
        var users = List.of(createUser(), createUserWithNoPhone(), createUserWithNoBirthDate(), createUserWithNoPhoneAndBirthdate());
        when(keycloakAdapter.getUsers(0, 100)).thenReturn(users);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer")
                        .param("start", "0")
                        .param("end", "100")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(users.stream().map(CustomerResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getCustomerById() throws Exception {
        var user = createUser();
        when(keycloakAdapter.getUser("123")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(CustomerResponse.from(user)), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void getCustomerDetailById() throws Exception {
        var user = createUser();
        var customer = CustomerResponse.from(user);
        var orders = List.of(new OrderResponse(1L, OrderStatus.DELIVERED, 5L));
        var payments = List.of(new PaymentResponse(5L, 260D));
        var customerDetail = CustomerDetailResponse.from(customer, orders, payments);
        when(keycloakAdapter.getUser("123")).thenReturn(user);
        when(orderRestClient.getByCustomerId("123", "abc")).thenReturn(orders);
        when(paymentRestClient.getByCustomerId("123", "abc")).thenReturn(payments);
        MvcResult mvcResult = mockMvc.perform(
                get("/customer/123/detail")
                        .header("authorization", "abc")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(customerDetail), bodyOf(mvcResult));
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

    private UserRepresentation createUserWithNoPhone() {
        var user = new UserRepresentation();
        user.setId("456");
        user.setFirstName("darth");
        user.setLastName("vader");
        user.setEmail("vader@mail.com");
        var birthDate = List.of(LocalDate.of(1970, 2, 12).toString());
        user.setAttributes(Map.of("birthDate", birthDate));
        return user;
    }

    private UserRepresentation createUserWithNoBirthDate() {
        var user = new UserRepresentation();
        user.setId("789");
        user.setFirstName("tommy");
        user.setLastName("shelby");
        user.setEmail("garrison@mail.com");
        var phoneNumber = List.of("+47573926");
        user.setAttributes(Map.of("phoneNumber", phoneNumber));
        return user;
    }

    private UserRepresentation createUserWithNoPhoneAndBirthdate() {
        var user = new UserRepresentation();
        user.setId("012");
        user.setFirstName("mike");
        user.setLastName("ehrmantrout");
        user.setEmail("mike@mail.com");
        return user;
    }
}
