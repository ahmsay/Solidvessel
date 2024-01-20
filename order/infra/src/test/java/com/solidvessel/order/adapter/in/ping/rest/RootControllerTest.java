package com.solidvessel.order.adapter.in.ping.rest;

import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RootController.class})
public class RootControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockCustomer
    void ping() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/ping")
        ).andExpect(status().isOk()).andReturn();
        String host = InetAddress.getLocalHost().getHostName();
        assertEquals("Order microservice works. Host: %s".formatted(host), bodyOf(mvcResult));
    }
}
