package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.entity.ProductEmbeddable;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private PaymentDBQueryAdapter paymentDBQueryAdapter;

    @Test
    public void getAll() {
        var product1 = new ProductEmbeddable(1L, 6, "pillow", 10D);
        var payment1 = new PaymentJpaEntity(null, "123", List.of(product1), 60D);
        var product2 = new ProductEmbeddable(2L, 3, "scissors", 5D);
        var payment2 = new PaymentJpaEntity(null, "456", List.of(product2), 15D);
        persistEntity(payment1);
        persistEntity(payment2);
        var payments = paymentDBQueryAdapter.getAll();
        assertEquals(60D, payments.getFirst().getTotalPrice());
        assertEquals("scissors", payments.get(1).getProducts().getFirst().getName());
    }

    @Test
    public void getById() {
        var product = new ProductEmbeddable(1L, 2, "phone", 500D);
        var payment = new PaymentJpaEntity(null, "123", List.of(product), 1000D);
        persistEntity(payment);
        assertEquals("123", paymentDBQueryAdapter.getById(payment.getId()).getCustomerId());
    }

    @Test
    public void getByCustomerId() {
        var product = new ProductEmbeddable(1L, 3, "apple", 3D);
        var payment = new PaymentJpaEntity(null, "789", List.of(product), 9D);
        persistEntity(payment);
        var payments = paymentDBQueryAdapter.getByCustomerId("789");
        assertEquals(3, payments.getFirst().getProducts().getFirst().getQuantity());
    }
}
