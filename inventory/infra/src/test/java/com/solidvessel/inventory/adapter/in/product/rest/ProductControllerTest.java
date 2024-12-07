package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductToCartRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.ChangeProductAvailabilityRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.UpdateProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductAvailability;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.UnavailableReason;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.*;
import com.solidvessel.inventory.product.service.command.DeleteProductCommand;
import com.solidvessel.inventory.product.service.command.DeleteProductsCommand;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private UpdateProductCommandService updateProductCommandService;

    @MockBean
    private DeleteProductCommandService deleteProductCommandService;

    @MockBean
    private DeleteProductsCommandService deleteProductsCommandService;

    @MockBean
    private ChangeProductAvailabilityCommandService changeProductAvailabilityCommandService;

    @Test
    @WithMockCustomer
    public void getProducts() throws Exception {
        var queryOptions = new QueryOptions(0);
        var products = List.of(Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 10));
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
        var product = Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 10);
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
                Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 10),
                Product.newProduct("shirt", 20D, ProductCategory.CLOTHING, 5)
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
        var savedProduct = Product.builder().id(1L).name("desk").price(150D).category(ProductCategory.FURNITURE).quantity(5).build();
        when(productQueryPort.getById(1L)).thenReturn(savedProduct);
        MvcResult mvcResult = mockMvc.perform(
                get("/product/1/isAvailable")
                        .queryParam("quantity", "5")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(ProductAvailability.available()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void changeProductAvailability() throws Exception {
        var request = new ChangeProductAvailabilityRequest(1L, false);
        var availability = new ProductAvailability(false, UnavailableReason.NOT_AVAILABLE_IN_REGION);
        when(changeProductAvailabilityCommandService.execute(request.toCommand())).thenReturn(availability);
        MvcResult mvcResult = mockMvc.perform(
                post("/product/changeAvailability")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(ProductAvailability.notAvailableInRegion()), bodyOf(mvcResult));
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

    @Test
    @WithMockManager
    public void updateProduct() throws Exception {
        var request = new UpdateProductRequest(1L, "Dark Saber", 15D, ProductCategory.ELECTRONICS, 9);
        var savedProduct = Product.builder().id(1L).name("Dark Saber").price(15D).category(ProductCategory.ELECTRONICS).quantity(9).build();
        when(updateProductCommandService.execute(request.toCommand())).thenReturn(savedProduct);
        MvcResult mvcResult = mockMvc.perform(
                put("/product")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(ProductResponse.from(savedProduct)), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void deleteProduct() throws Exception {
        when(deleteProductCommandService.execute(new DeleteProductCommand(1L))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockManager
    public void deleteProducts() throws Exception {
        when(deleteProductsCommandService.execute(new DeleteProductsCommand(List.of(1L, 2L)))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/product/ids")
                        .queryParam("ids", "1", "2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
