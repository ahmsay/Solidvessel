package com.solidvessel.payment.adapter.in.cart.rest;

import com.solidvessel.payment.adapter.in.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.payment.adapter.in.cart.rest.response.CartResponse;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.ClearCartCommandService;
import com.solidvessel.payment.cart.service.RemoveFromCartCommandService;
import com.solidvessel.payment.cart.service.command.ClearCartCommand;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CartController.class})
public class CartControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartQueryPort cartQueryPort;

    @MockBean
    private RemoveFromCartCommandService removeFromCartCommandService;

    @MockBean
    private ClearCartCommandService clearCartCommandService;

    @Test
    @WithMockCustomer
    void listCart() throws Exception {
        var products = Map.of(
                1L, new Product(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 3),
                2L, new Product(2L, "shirt", 20D, ProductCategory.CLOTHING, 2)
        );
        var cart = new Cart("123", products);
        var cartResponse = CartResponse.from(cart);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);
        MvcResult mvcResult = mockMvc.perform(
                get("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(cartResponse), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void removeFromCart() throws Exception {
        var request = new RemoveFromCartRequest(1L);
        when(removeFromCartCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/cart")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void clearCart() throws Exception {
        when(clearCartCommandService.execute(new ClearCartCommand(SessionUtil.getCurrentUserId()))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/cart/clear")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
