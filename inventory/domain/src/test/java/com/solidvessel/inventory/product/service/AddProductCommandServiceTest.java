package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class AddProductCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductPort productPort;

    @Test
    void addProduct() {
        var command = new AddProductCommand("macbook", 1200D, ProductCategory.ELECTRONICS, 3);
        var commandService = new AddProductCommandService(productPort);
        var operationResult = commandService.execute(command);
        verify(productPort).save(any(Product.class));
        assertEquals(operationResult.resultType(), ResultType.SUCCESS);
    }
}
