package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CartDBQueryAdapter cartDBQueryAdapter;

    @Test
    void getByCustomerId() {
        var productEmbeddable = new ProductEmbeddable(1L, "desk", 20D, ProductCategory.FURNITURE, 4);
        var cartJpaEntity = persistEntity(new CartJpaEntity("123", List.of(productEmbeddable)));
        var cart = cartDBQueryAdapter.getByCustomerId(cartJpaEntity.getCustomerId());
        assertEquals(cartJpaEntity.getId(), cart.getId());
        assertEquals(cartJpaEntity.getCustomerId(), cart.getCustomerId());
        assertEquals(cartJpaEntity.getProducts().size(), cart.getProductList().size());
        var product = cart.getProducts().get(productEmbeddable.getProductId());
        assertEquals(productEmbeddable.getProductId(), product.getId());
        assertEquals(productEmbeddable.getName(), product.getName());
        assertEquals(productEmbeddable.getPrice(), product.getPrice());
        assertEquals(productEmbeddable.getCategory(), product.getCategory());
        assertEquals(productEmbeddable.getQuantity(), product.getQuantity());
    }

    @Test
    void getByCustomerIdEmptyCart() {
        var cart = cartDBQueryAdapter.getByCustomerId("123");
        assertNull(cart.getId());
        assertEquals("123", cart.getCustomerId());
        assertTrue(cart.isEmpty());
    }
}
