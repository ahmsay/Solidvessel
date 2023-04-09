package com.solidvessel.payment.infra.adapter.cart.db;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.infra.adapter.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.infra.adapter.cart.db.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDBAdapter implements CartPort {

    private final CartRepository cartRepository;

    @Override
    public void save(Cart cart) {
        cartRepository.save(CartJpaEntity.from(cart));
    }
}
