package com.solidvessel.inventory.product.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void createNewProduct() {
        var product = Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 5);
        assertNull(product.getId());
        assertEquals("macbook", product.getName());
        assertEquals("macbook", product.getName());
        assertEquals(1200D, product.getPrice());
        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
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
        assertTrue(product.isInStock(3));
        assertTrue(product.isInStock(5));
        assertFalse(product.isInStock(10));
    }
}
