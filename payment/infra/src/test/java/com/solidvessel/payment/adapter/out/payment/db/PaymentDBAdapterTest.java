package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class PaymentDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private PaymentDBAdapter paymentDBAdapter;

    @Test
    public void savePayment() {
        var products = Map.of(1L, new Product(1L, "phone", 500D, ProductCategory.ELECTRONICS, 2));
        var cart = new Cart("123", products);
        var payment = Payment.newPayment("123", cart);
        paymentDBAdapter.save(payment);
    }
}
