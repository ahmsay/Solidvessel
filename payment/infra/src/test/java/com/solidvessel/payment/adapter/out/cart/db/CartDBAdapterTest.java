package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CartDBAdapter cartDBAdapter;

    @Test
    public void saveCart() {
        var cart = Cart.newCart("123");
        cart.addProduct(1L, 5);
        cartDBAdapter.save(cart);
    }
}
