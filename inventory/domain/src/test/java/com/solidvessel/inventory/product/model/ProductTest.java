package com.solidvessel.inventory.product.model;

import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void createNewProduct() {
        var product = Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 5);
        assertNull(product.getId());
        assertEquals("macbook", product.getName());
        assertEquals(1200D, product.getPrice());
        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
        assertEquals(5, product.getQuantity());
        assertEquals(true, product.getIsAvailableInRegion());
    }

    @Test
    void decreaseQuantity() {
        var product = new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 5, true);
        product.decreaseQuantity(2);
        assertEquals(3, product.getQuantity());
    }

    @Test
    void isInStock() {
        var product = new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 5, true);
        assertTrue(product.isAvailable(3).getIsAvailable());
        assertTrue(product.isAvailable(5).getIsAvailable());
        assertFalse(product.isAvailable(10).getIsAvailable());
        assertEquals(UnavailableReason.NOT_IN_STOCKS, product.isAvailable(10).getUnavailableReason());
    }

    @Test
    void isAvailableInRegion() {
        var product1 = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).quantity(6).isAvailableInRegion(true).build();
        var product2 = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).quantity(6).isAvailableInRegion(false).build();
        assertTrue(product1.isAvailable(3).getIsAvailable());
        var availability = product2.isAvailable(3);
        assertFalse(availability.getIsAvailable());
        assertEquals(UnavailableReason.NOT_AVAILABLE_IN_REGION, availability.getUnavailableReason());
    }

    @Test
    void update() {
        var product = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).quantity(6).build();
        product.update(new UpdateProductCommand(1L, "milk", 15D, ProductCategory.FURNITURE, 6));
        assertEquals("milk", product.getName());
        assertEquals(15D, product.getPrice());
        assertEquals(ProductCategory.FURNITURE, product.getCategory());
        assertEquals(6, product.getQuantity());
        assertEquals(true, product.getIsAvailableInRegion());
    }

    @Test
    void changeAvailability() {
        var product = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).quantity(6).build();
        product.changeAvailability(false);

        var availability = product.isAvailable(0);
        assertFalse(availability.getIsAvailable());
        assertEquals(UnavailableReason.NOT_AVAILABLE_IN_REGION, availability.getUnavailableReason());
    }
}
