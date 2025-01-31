package com.solidvessel.payment.product.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void increaseQuantity() {
        var product = new Product(2L, "sickle", 5D, ProductCategory.FURNITURE, 3);
        product.increaseQuantity(4);
        assertEquals(7, product.getQuantity());
    }

    @Test
    void decreaseQuantity() {
        var product = new Product(2L, "sickle", 5D, ProductCategory.FURNITURE, 3);
        product.decreaseQuantity();
        assertEquals(2, product.getQuantity());
    }

    @Test
    void getTotalPrice() {
        var product = new Product(2L, "sickle", 5D, ProductCategory.FURNITURE, 3);
        assertEquals(15D, product.getTotalPrice());
    }
}
