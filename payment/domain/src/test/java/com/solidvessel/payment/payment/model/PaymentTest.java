package com.solidvessel.payment.payment.model;

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
        Map<Long, Product> products = new HashMap<>();
        products.put(1L, new Product(1L, "sickle", 9D, ProductCategory.TOOL, 4));
        products.put(4L, new Product(4L, "chair", 15D, ProductCategory.FURNITURE, 2));
        var payment = Payment.newPayment("123", products);
        assertNull(payment.getId());
        assertEquals("123", payment.getCustomerId());
        assertEquals(4, payment.getProducts().getFirst().getQuantity());
        assertEquals(2, payment.getProducts().get(1).getQuantity());
        assertEquals(946D, payment.getTotalPrice());
    }
}
