package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.product.event.InventoryCheckedEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateProductQuantitiesCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductPort productPort;

    @Mock
    private ProductQueryPort productQueryPort;

    @Mock
    private EventPublisher<InventoryCheckedEvent> inventoryCheckedEventPublisher;

    Product product1;

    Product product2;

    @Test
    void updateProductQuantities() {
        var productQuantities = new HashMap<Long, Integer>();
        productQuantities.put(1L, 5);
        productQuantities.put(3L, 2);
        var command = new UpdateProductQuantitiesCommand(1L, productQuantities);
        var commandService = new UpdateProductQuantitiesCommandService(productPort, productQueryPort, inventoryCheckedEventPublisher);
        when(productQueryPort.getByIds(List.of(1L, 3L))).thenReturn(retrieveProducts());
        var operationResult = commandService.execute(command);
        verify(productPort).saveProducts(List.of(product1, product2));
        verify(inventoryCheckedEventPublisher).publish(new InventoryCheckedEvent(1L, true));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertEquals(1, product1.getQuantity());
        assertEquals(2, product2.getQuantity());
    }

    @Test
    void productsAreNotAvailable() {
        var productQuantities = new HashMap<Long, Integer>();
        productQuantities.put(1L, 10);
        productQuantities.put(3L, 15);
        var command = new UpdateProductQuantitiesCommand(1L, productQuantities);
        var commandService = new UpdateProductQuantitiesCommandService(productPort, productQueryPort, inventoryCheckedEventPublisher);
        when(productQueryPort.getByIds(List.of(1L, 3L))).thenReturn(retrieveProducts());
        assertThrows(InventoryDomainException.class, () -> commandService.execute(command));
        verify(inventoryCheckedEventPublisher).publish(new InventoryCheckedEvent(1L, false));
        verifyNoInteractions(productPort);
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
