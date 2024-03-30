package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.product.event.ProductsCheckedEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.shared.event.EventPublisher;
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
    private EventPublisher<ProductsCheckedEvent> productsCheckedEventPublisher;

    Product product1;

    Product product2;

    @Test
    void updateProductQuantities() {
        var productQuantities = new HashMap<Long, Integer>();
        productQuantities.put(1L, 5);
        productQuantities.put(3L, 2);
        var event = new PaymentSavedEvent(1L, "123", productQuantities);
        var commandService = new UpdateProductQuantitiesCommandService(productPort, productQueryPort, productsCheckedEventPublisher);
        when(productQueryPort.getByIds(List.of(1L, 3L))).thenReturn(retrieveProducts());
        commandService.execute(event);
        verify(productPort).saveProducts(List.of(product1, product2));
        verify(productsCheckedEventPublisher).publish(new ProductsCheckedEvent(1L, true, "123"));
        assertEquals(1, product1.getQuantity());
        assertEquals(2, product2.getQuantity());
    }

    @Test
    void productsAreNotAvailable() {
        var productQuantities = new HashMap<Long, Integer>();
        productQuantities.put(1L, 10);
        productQuantities.put(3L, 15);
        var event = new PaymentSavedEvent(1L, "123", productQuantities);
        var commandService = new UpdateProductQuantitiesCommandService(productPort, productQueryPort, productsCheckedEventPublisher);
        when(productQueryPort.getByIds(List.of(1L, 3L))).thenReturn(retrieveProducts());
        assertThrows(InventoryDomainException.class, () -> commandService.execute(event));
        verify(productsCheckedEventPublisher).publish(new ProductsCheckedEvent(1L, false, "123"));
        verifyNoInteractions(productPort);
    }

    private List<Product> retrieveProducts() {
        product1 = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).quantity(6).build();
        product2 = Product.builder().id(3L).name("pants").price(10D).category(ProductCategory.CLOTHING).quantity(4).build();
        var products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        return products;
    }
}
