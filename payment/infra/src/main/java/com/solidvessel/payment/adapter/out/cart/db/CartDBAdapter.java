package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.adapter.out.cart.db.mapper.CartJpaMapper;
import com.solidvessel.payment.adapter.out.cart.db.repository.CartRepository;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDBAdapter implements CartPort {

    private final CartRepository cartRepository;

    private final CartJpaMapper cartJpaMapper;

    @CacheEvict(value = "cart", key = "#cart.customerId")
    @Override
    public void save(Cart cart) {
        cartRepository.save(cartJpaMapper.toJpaEntity(cart));
    }
}
