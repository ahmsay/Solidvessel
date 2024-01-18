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
        assertEquals(5, payment.getProducts().getFirst().getQuantity());
        assertEquals(9, payment.getProducts().get(1).getQuantity());
        assertEquals(1215D, payment.getTotalPrice());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
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
        return new Cart(1L, "123", products);
    }
}
