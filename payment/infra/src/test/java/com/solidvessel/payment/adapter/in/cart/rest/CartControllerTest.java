package com.solidvessel.payment.adapter.in.cart.rest;

import com.solidvessel.payment.adapter.in.cart.rest.datamodel.CartDataModel;
import com.solidvessel.payment.adapter.in.cart.rest.request.AddToCartRequest;
import com.solidvessel.payment.adapter.in.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.AddToCartCommandService;
import com.solidvessel.payment.cart.service.RemoveFromCartCommandService;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.port.ProductQueryPort;
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

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CartController.class})
public class CartControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddToCartCommandService addToCartCommandService;

    @MockBean
    private RemoveFromCartCommandService removeFromCartCommandService;

    @MockBean
    private CartQueryPort cartQueryPort;

    @MockBean
    private ProductQueryPort productQueryPort;

    @Test
    @WithMockCustomer
    public void addToCart() throws Exception {
        var request = new AddToCartRequest(1L, 3);
        when(addToCartCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/cart")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void listCart() throws Exception {
        var productQuantities = Map.of(1L, 3, 2L, 5);
        var cart = new Cart(1L, "123", productQuantities);
        var products = List.of(
                new Product(1L, 3, "macbook", 1200D),
                new Product(2L, 2, "shirt", 20D)
        );
        var cartDataModel = CartDataModel.from(cart, products);
        when(cartQueryPort.getByCustomerId(anyString())).thenReturn(cart);
        when(productQueryPort.getProductsOfCart(Set.of(1L, 2L))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(
                get("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(cartDataModel), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void removeFromCart() throws Exception {
        var request = new RemoveFromCartRequest(1L);
        when(removeFromCartCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/cart")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
