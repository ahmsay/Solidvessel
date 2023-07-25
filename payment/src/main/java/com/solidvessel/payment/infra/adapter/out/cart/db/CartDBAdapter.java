package com.solidvessel.payment.infra.adapter.out.cart.db;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.infra.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.infra.adapter.out.cart.db.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDBAdapter implements CartPort {

    private final CartRepository cartRepository;

    @Override
    public Cart getByCustomerId(Long customerId) {
        CartJpaEntity cart = cartRepository.findByCustomerId(customerId).orElseThrow(() -> new EntityNotFoundException("Cart is not found."));
        return cart.toDomainModel();
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(CartJpaEntity.from(cart));
    }
}
