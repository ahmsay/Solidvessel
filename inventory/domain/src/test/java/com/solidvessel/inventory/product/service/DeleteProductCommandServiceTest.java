package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.DeleteProductCommand;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class DeleteProductCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductPort productPort;

    @Test
    void deleteProduct() {
        var command = new DeleteProductCommand(1L);
        var commandService = new DeleteProductCommandService(productPort);
        commandService.execute(command);
        verify(productPort).delete(1L);
    }
}
