package com.solidvessel.payment.domain.product.port;

import com.solidvessel.payment.domain.product.model.Product;

import java.util.Optional;

public interface ProductPort {

    Optional<Product> getById(Long productId);
}
