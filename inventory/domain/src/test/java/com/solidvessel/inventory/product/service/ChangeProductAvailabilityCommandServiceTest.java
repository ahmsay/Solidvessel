package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.UnavailableReason;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.ChangeProductAvailabilityCommand;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChangeProductAvailabilityCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductQueryPort productQueryPort;

    @Mock
    private ProductPort productPort;

    @Test
    void changeProductAvailability() {
        var command = new ChangeProductAvailabilityCommand(3L, false);
        var commandService = new ChangeProductAvailabilityCommandService(productQueryPort, productPort);
        var product = Product.builder().id(2L).name("Oil").price(250D).category(ProductCategory.CLOTHING).quantity(5).build();
        when(productQueryPort.getById(3L)).thenReturn(product);
        var availability = commandService.execute(command);
        verify(productPort).save(Product.builder().id(2L).name("Oil").price(250D).category(ProductCategory.CLOTHING).quantity(5).isAvailableInRegion(false).build());
        assertFalse(availability.getIsAvailable());
        assertEquals(UnavailableReason.NOT_AVAILABLE_IN_REGION, availability.getUnavailableReason());
    }
}
