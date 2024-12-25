package com.solidvessel.payment.cart.model;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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
        cart.addProduct(createProduct(5L, 3));

        var product1 = cart.getProducts().get(1L);
        assertEquals(1L, product1.getId());
        assertEquals("table", product1.getName());
        assertEquals(5D, product1.getPrice());
        assertEquals(ProductCategory.FURNITURE, product1.getCategory());
        assertEquals(2, product1.getQuantity());

        var product2 = cart.getProducts().get(5L);
        assertEquals(5L, product2.getId());
        assertEquals("table", product2.getName());
        assertEquals(5D, product2.getPrice());
        assertEquals(ProductCategory.FURNITURE, product2.getCategory());
        assertEquals(13, product2.getQuantity());
    }

    @Test
    void doesProductExist() {
        var cart = Cart.builder().id(1L).customerId("123").products(new HashMap<>()).build();
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

    @Test
    void getProductList() {
        var cart = Cart.newCart("123");
        var product1 = createProduct(1L, 2);
        var product2 = createProduct(5L, 10);
        cart.addProduct(product1);
        cart.addProduct(product2);
        assertEquals(List.of(product1, product2), cart.getProductList());
    }

    @Test
    void getTotalPrice() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.addProduct(createProduct(5L, 10));
        assertEquals(60D, cart.getTotalPrice());
    }

    @Test
    void getProductQuantities() {
        var cart = Cart.newCart("123");
        cart.addProduct(createProduct(1L, 2));
        cart.addProduct(createProduct(5L, 10));
        var quantities = cart.getProductQuantities();
        assertEquals(2, quantities.get(1L));
        assertEquals(10, quantities.get(5L));
    }

    private Product createProduct(Long id, Integer quantity) {
        return new Product(id, "table", 5D, ProductCategory.FURNITURE, quantity);
    }
}
