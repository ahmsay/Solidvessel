package com.solidvessel.payment.cart.model;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void createNewCart() {
        var cart = Cart.newCart("123");
        assertNull(cart.getId());
        assertEquals("123", cart.getCustomerId());
        assertEquals(new HashMap<>(), cart.getProducts());
    }

    @Test
    void addProduct() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.addProduct(createProduct(5L, 10));
        assertEquals(2, cart.getProducts().get(1L).getQuantity());
        assertEquals(10, cart.getProducts().get(5L).getQuantity());
    }

    @Test
    void doesProductExist() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        assertTrue(cart.doesProductExist(1L));
        assertFalse(cart.doesProductExist(3L));
    }

    @Test
    void removeProduct() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.removeProduct(1L);
        assertTrue(cart.getProducts().containsKey(1L));
        cart.removeProduct(1L);
        assertFalse(cart.getProducts().containsKey(1L));
    }

    @Test
    void isEmpty() {
        var cart1 = Cart.newCart("123");
        cart1.addProduct(createProduct(1L, 2));
        var cart2 = Cart.newCart("123");
        assertFalse(cart1.isEmpty());
        assertTrue(cart2.isEmpty());
    }

    @Test
    void empty() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.empty();
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void getProductIds() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.addProduct(createProduct(5L, 10));
        assertEquals(Set.of(1L, 5L), cart.getProductIds());
    }

    private Product createProduct(Long id, int quantity) {
        return new Product(id, "table", 5D, ProductCategory.FURNITURE, quantity);
    }
}
