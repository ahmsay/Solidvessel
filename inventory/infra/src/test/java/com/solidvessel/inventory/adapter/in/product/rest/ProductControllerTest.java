package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.AddProductCommandService;
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

@WebMvcTest(controllers = {ProductController.class})
public class ProductControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductQueryPort productQueryPort;

    @MockBean
    private AddProductCommandService addProductCommandService;

    @Test
    @WithMockCustomer
    public void getAllProducts() throws Exception {
        var products = List.of(new Product(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 10));
        when(productQueryPort.getAll()).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(
                get("/product")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(products.stream().map(ProductResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void getProductById() throws Exception {
        var product = new Product(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 10);
        when(productQueryPort.getById(1L)).thenReturn(product);
        MvcResult mvcResult = mockMvc.perform(
                get("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(ProductResponse.from(product)), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void getProductsByIds() throws Exception {
        var products = List.of(
                new Product(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 10),
                new Product(2L, "shirt", 20D, ProductCategory.CLOTHING, 5)
        );
        when(productQueryPort.getByIds(List.of(1L, 2L))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(
                get("/product/ids")
                        .queryParam("ids", "1", "2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(products.stream().map(ProductResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void addProduct() throws Exception {
        var request = new AddProductRequest("desk", 150D, ProductCategory.FURNITURE, 5);
        when(addProductCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/product")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void isProductAvailable() throws Exception {
        when(productQueryPort.isAvailable(1L, 5)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(
                get("/product/isAvailable")
                        .queryParam("id", "1")
                        .queryParam("quantity", "5")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(true), bodyOf(mvcResult));
    }
}
