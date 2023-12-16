package com.solidvessel.inventory.product.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        var product = new Product(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 5);
        product.decreaseQuantity(2);
        assertEquals(3, product.getQuantity());
    }
}
