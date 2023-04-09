package com.solidvessel.payment.domain.cart.port;

import com.solidvessel.payment.domain.cart.model.Cart;

public interface CartPort {

    Cart getByCustomerId(Long customerId);

    void save(Cart cart);
}
