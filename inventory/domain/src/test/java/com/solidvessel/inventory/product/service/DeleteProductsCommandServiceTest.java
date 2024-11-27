package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.service.command.DeleteProductsCommand;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;

public class DeleteProductsCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductPort productPort;

    @Test
    void deleteProducts() {
        var ids = List.of(1L, 2L, 3L);
        var command = new DeleteProductsCommand(ids);
        var commandService = new DeleteProductsCommandService(productPort);
        commandService.execute(command);
        verify(productPort).deleteByIds(ids);
    }
}
