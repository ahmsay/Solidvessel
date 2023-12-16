package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddProductCommandServiceTest {

    @Mock
    private ProductPort productPort;

    @Test
    void addProduct() {
        var command = new AddProductCommand("macbook", 1200D, ProductCategory.ELECTRONICS, 3);
        var commandService = new AddProductCommandService(productPort);
        var operationResult = commandService.execute(command);
        assertEquals(operationResult.resultType(), ResultType.SUCCESS);
    }
}
