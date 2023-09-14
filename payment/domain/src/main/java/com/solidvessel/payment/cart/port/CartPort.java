package com.solidvessel.payment.cart.port;

import com.solidvessel.payment.cart.model.Cart;

public interface CartPort {

    void save(Cart cart);
}
