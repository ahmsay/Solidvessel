package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductToCartRequest;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.AddProductCommandService;
import com.solidvessel.inventory.product.service.AddProductToCartCommandService;
import com.solidvessel.shared.query.QueryOptions;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @MockBean
    private AddProductToCartCommandService addProductToCartCommandService;

    @Test
    @WithMockCustomer
    public void getProducts() throws Exception {
        var queryOptions = new QueryOptions(0);
        var products = List.of(new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 10));
        when(productQueryPort.getProducts(queryOptions)).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(
                get("/product")
                        .param("pageNumber", "0")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(products.stream().map(ProductResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void getProductById() throws Exception {
        var product = new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 10);
        when(productQueryPort.getById(anyLong())).thenReturn(product);
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
                new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 10),
                new Product("shirt", 20D, ProductCategory.CLOTHING, 5)
        );
        when(productQueryPort.getByIds(anyList())).thenReturn(products);
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
        var savedProduct = Product.builder().id(1L).name("desk").price(150D).category(ProductCategory.FURNITURE).quantity(5).build();
        when(addProductCommandService.execute(request.toCommand())).thenReturn(savedProduct);
        MvcResult mvcResult = mockMvc.perform(
                post("/product")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(ProductResponse.from(savedProduct)), bodyOf(mvcResult));
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

    @Test
    @WithMockCustomer
    public void addProductToCart() throws Exception {
        var request = new AddProductToCartRequest(3);
        when(addProductToCartCommandService.execute(request.toCommand(1L))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/product/1/addToCart")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
