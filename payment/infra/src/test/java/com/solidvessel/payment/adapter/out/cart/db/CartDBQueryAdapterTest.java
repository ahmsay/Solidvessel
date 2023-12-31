package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.cart.db.entity.CartProductEmbeddable;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CartDBQueryAdapter cartDBQueryAdapter;

    @Test
    public void getByCustomerId() {
        var cartProduct = new CartProductEmbeddable(1L, 4);
        var cartJpaEntity = new CartJpaEntity(null, "123", List.of(cartProduct));
        persistEntity(cartJpaEntity);
        var cart = cartDBQueryAdapter.getByCustomerId("123");
        assertEquals(4, cart.getProductQuantities().get(1L));
    }
}
