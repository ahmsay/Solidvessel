package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.cart.db.repository.CartRepository;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDBQueryAdapter implements CartQueryPort {

    private final CartRepository cartRepository;

    @Override
    public Cart getByCustomerId(Long customerId) {
        CartJpaEntity cart = cartRepository.findByCustomerId(customerId).orElseThrow(() -> new EntityNotFoundException("Cart is not found."));
        return cart.toDomainModel();
    }
}
