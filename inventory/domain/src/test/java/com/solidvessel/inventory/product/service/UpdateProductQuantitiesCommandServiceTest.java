package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateProductQuantitiesCommandServiceTest {

    @Mock
    private ProductPort productPort;

    @Mock
    private ProductQueryPort productQueryPort;

    Product product1;

    Product product2;

    @Test
    void updateProductQuantities() {
        var productQuantities = new HashMap<Long, Integer>();
        productQuantities.put(1L, 5);
        productQuantities.put(3L, 2);
        var command = new UpdateProductQuantitiesCommand(productQuantities);
        var commandService = new UpdateProductQuantitiesCommandService(productPort, productQueryPort);
        when(productQueryPort.getByIds(Set.of(1L, 3L))).thenReturn(retrieveProducts());
        var operationResult = commandService.execute(command);
        verify(productPort).saveProducts(List.of(product1, product2));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertEquals(1, product1.getQuantity());
        assertEquals(2, product2.getQuantity());
    }

    private List<Product> retrieveProducts() {
        product1 = new Product(1L, "shirt", 5D, ProductCategory.CLOTHING, 6);
        product2 = new Product(3L, "pants", 10D, ProductCategory.CLOTHING, 4);
        var products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        return products;
    }
}
