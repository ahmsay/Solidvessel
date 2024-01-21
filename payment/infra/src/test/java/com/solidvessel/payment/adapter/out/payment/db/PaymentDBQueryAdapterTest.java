package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private PaymentDBQueryAdapter paymentDBQueryAdapter;

    @Test
    public void getAll() {
        var product1 = new ProductEmbeddable(1L, "pillow", 10D, ProductCategory.CLOTHING, 6);
        var payment1 = new PaymentJpaEntity(null, "123", List.of(product1), 60D, PaymentStatus.APPROVED);
        var product2 = new ProductEmbeddable(2L, "scissors", 5D, ProductCategory.TOOL, 3);
        var payment2 = new PaymentJpaEntity(null, "456", List.of(product2), 15D, PaymentStatus.PENDING);
        persistEntity(payment1);
        persistEntity(payment2);
        var payments = paymentDBQueryAdapter.getAll();
        assertEquals(60D, payments.getFirst().getTotalPrice());
        assertEquals("scissors", payments.get(1).getProducts().getFirst().getName());
    }

    @Test
    public void getById() {
        var productEmbeddable = new ProductEmbeddable(1L, "phone", 500D, ProductCategory.ELECTRONICS, 2);
        var paymentJpaEntity = persistEntity(new PaymentJpaEntity(null, "123", List.of(productEmbeddable), 1000D, PaymentStatus.APPROVED));
        var payment = paymentDBQueryAdapter.getById(paymentJpaEntity.getId());
        assertEquals(paymentJpaEntity.getId(), payment.getId());
        assertEquals(paymentJpaEntity.getCustomerId(), payment.getCustomerId());
        assertEquals(paymentJpaEntity.getTotalPrice(), payment.getTotalPrice());
        assertEquals(paymentJpaEntity.getProducts().size(), payment.getProducts().size());
        assertEquals(paymentJpaEntity.getStatus(), PaymentStatus.APPROVED);
        var product = payment.getProducts().getFirst();
        assertEquals(productEmbeddable.getProductId(), product.getId());
        assertEquals(productEmbeddable.getName(), product.getName());
        assertEquals(productEmbeddable.getPrice(), product.getPrice());
        assertEquals(productEmbeddable.getCategory(), product.getCategory());
        assertEquals(productEmbeddable.getQuantity(), product.getQuantity());
    }

    @Test
    public void getByCustomerId() {
        var product = new ProductEmbeddable(1L, "apple", 3D, ProductCategory.FURNITURE, 3);
        var payment = new PaymentJpaEntity(null, "789", List.of(product), 9D, PaymentStatus.APPROVED);
        persistEntity(payment);
        var payments = paymentDBQueryAdapter.getByCustomerId("789");
        assertEquals(3, payments.getFirst().getProducts().getFirst().getQuantity());
    }
}
