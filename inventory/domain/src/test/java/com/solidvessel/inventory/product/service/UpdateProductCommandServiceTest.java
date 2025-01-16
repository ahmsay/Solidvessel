package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateProductCommandServiceTest extends BaseUnitTest {

    @Mock
    private ProductQueryPort productQueryPort;

    @Mock
    private ProductPort productPort;

    @Test
    void updateProduct() {
        var command = new UpdateProductCommand(2L, "Dinosaur", 500D, ProductCategory.FURNITURE, 10);
        var commandService = new UpdateProductCommandService(productQueryPort, productPort);
        var product = Product.builder().id(2L).name("Oil").price(250D).category(ProductCategory.CLOTHING).quantity(5).build();
        when(productQueryPort.getById(2L)).thenReturn(product);
        commandService.execute(command);
        verify(productPort).save(Product.builder().id(2L).name("Dinosaur").price(500D).category(ProductCategory.FURNITURE).quantity(10).build());
    }
}
