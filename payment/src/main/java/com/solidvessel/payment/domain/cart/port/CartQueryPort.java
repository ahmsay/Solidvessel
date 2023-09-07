package com.solidvessel.payment.domain.cart.port;

import com.solidvessel.payment.domain.cart.model.Cart;

public interface CartQueryPort {

    Cart getByCustomerId(Long customerId);
}
