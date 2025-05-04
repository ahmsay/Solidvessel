package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.product.event.ProductAvailableEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.AddProductToCartCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddProductToCartCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductQueryPort productQueryPort;

    @Mock
    private EventPublisher<ProductAvailableEvent> productAvailableEventEventPublisher;

    @Test
    void addProductToCart() {
        var command = new AddProductToCartCommand(1L, 4, "123");
        var commandService = new AddProductToCartCommandService(productQueryPort, productAvailableEventEventPublisher);
        var product = new Product("sickle", 5D, ProductCategory.TOOL, 10, true);
        when(productQueryPort.getById(1L)).thenReturn(product);
        var operationResult = commandService.execute(command);
        verify(productAvailableEventEventPublisher).publish(ProductAvailableEvent.from(product, command.quantity(), "123"));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void productNotAvailable() {
        var command = new AddProductToCartCommand(1L, 4, "123");
        var commandService = new AddProductToCartCommandService(productQueryPort, productAvailableEventEventPublisher);
        var product = new Product("sickle", 5D, ProductCategory.TOOL, 1, true);
        when(productQueryPort.getById(1L)).thenReturn(product);
        assertThrows(InventoryDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(productAvailableEventEventPublisher);
    }
}
