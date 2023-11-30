package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.cart.db.repository.CartRepository;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartDBQueryAdapter implements CartQueryPort {

    private final CartRepository cartRepository;

    @Override
    public Cart getByCustomerId(String customerId) {
        Optional<CartJpaEntity> cart = cartRepository.findByCustomerId(customerId);
        return cart.map(CartJpaEntity::toDomainModel).orElseGet(() -> Cart.newCart(customerId));
    }
}
