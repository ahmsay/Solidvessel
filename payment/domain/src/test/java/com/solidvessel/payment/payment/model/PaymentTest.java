package com.solidvessel.payment.payment.model;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {

    @Test
    void createNewPayment() {
        var payment = Payment.newPayment("123", createCart());
        assertNull(payment.getId());
        assertEquals("123", payment.getCustomerId());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals(2, payment.getProducts().size());
        assertEquals(1215D, payment.getTotalPrice());

        var product1 = payment.getProducts().getFirst();
        assertEquals(1L, product1.getId());
        assertEquals("sickle", product1.getName());
        assertEquals(234D, product1.getPrice());
        assertEquals(ProductCategory.TOOL, product1.getCategory());
        assertEquals(5, product1.getQuantity());

        var product2 = payment.getProducts().get(1);
        assertEquals(4L, product2.getId());
        assertEquals("chair", product2.getName());
        assertEquals(5D, product2.getPrice());
        assertEquals(ProductCategory.FURNITURE, product2.getCategory());
        assertEquals(9, product2.getQuantity());
    }

    @Test
    void approve() {
        var payment = Payment.newPayment("123", createCart());
        payment.approve();
        assertEquals(PaymentStatus.APPROVED, payment.getStatus());
    }

    @Test
    void cancel() {
        var payment = Payment.newPayment("123", createCart());
        payment.cancel();
        assertEquals(PaymentStatus.CANCELLED, payment.getStatus());
    }

    private Cart createCart() {
        Map<Long, Product> products = new HashMap<>();
        products.put(1L, new Product(1L, "sickle", 234D, ProductCategory.TOOL, 5));
        products.put(4L, new Product(4L, "chair", 5D, ProductCategory.FURNITURE, 9));
        return new Cart("123", products);
    }
}
