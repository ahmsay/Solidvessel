package com.solidvessel.payment.payment.model;

import com.solidvessel.payment.product.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {

    @Test
    void createNewPayment() {
        List<Product> productsFromInventory = new ArrayList<>();
        productsFromInventory.add(new Product(1L, 5, "laptop", 234D));
        productsFromInventory.add(new Product(4L, 9, "knife", 5D));

        Map<Long, Integer> productQuantities = new HashMap<>();
        productQuantities.put(1L, 4);
        productQuantities.put(4L, 2);
        var payment = Payment.newPayment("123", productsFromInventory, productQuantities);
        assertNull(payment.getId());
        assertEquals("123", payment.getCustomerId());
        assertEquals(4, payment.getProducts().getFirst().getQuantity());
        assertEquals(2, payment.getProducts().get(1).getQuantity());
        assertEquals(946D, payment.getTotalPrice());
    }
}
