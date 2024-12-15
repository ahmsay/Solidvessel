package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private PaymentDBAdapter paymentDBAdapter;

    @Test
    void createPayment() {
        var products = Map.of(1L, new Product(1L, "phone", 500D, ProductCategory.ELECTRONICS, 2));
        var cart = new Cart("123", products);
        var payment = Payment.newPayment("123", cart);
        paymentDBAdapter.create(payment);
    }

    @Test
    void updatePayment() {
        var productEmbeddable = new ProductEmbeddable(1L, "phone", 500D, ProductCategory.ELECTRONICS, 2);
        List<ProductEmbeddable> products = new ArrayList<>();
        products.add(productEmbeddable);
        var paymentJpaEntity = PaymentJpaEntity.builder()
                .customerId("123")
                .products(products)
                .totalPrice(1000D)
                .status(PaymentStatus.PENDING)
                .build();
        var paymentJpaEntityFromDb = persistEntity(paymentJpaEntity);
        var paymentFromDb = paymentJpaEntityFromDb.toDomainModel();
        paymentFromDb.approve();
        paymentDBAdapter.update(paymentFromDb);
    }
}
