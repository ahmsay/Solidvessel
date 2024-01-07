package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.cart.db.entity.CartProductEmbeddable;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CartDBQueryAdapter cartDBQueryAdapter;

    @Test
    public void getByCustomerId() {
        var cartProductEmbeddable = new CartProductEmbeddable(1L, 4);
        var cartJpaEntity = persistEntity(new CartJpaEntity(null, "123", List.of(cartProductEmbeddable)));
        var cart = cartDBQueryAdapter.getByCustomerId(cartJpaEntity.getCustomerId());
        assertEquals(cartJpaEntity.getId(), cart.getId());
        assertEquals(cartJpaEntity.getCustomerId(), cart.getCustomerId());
        var productQuantity = cart.getProductQuantities().get(cartProductEmbeddable.getProductId());
        assertEquals(cartProductEmbeddable.getQuantity(), productQuantity);
    }

    @Test
    public void getByCustomerIdEmptyCart() {
        var cart = cartDBQueryAdapter.getByCustomerId("123");
        assertNull(cart.getId());
        assertEquals("123", cart.getCustomerId());
        assertTrue(cart.isEmpty());
    }
}
