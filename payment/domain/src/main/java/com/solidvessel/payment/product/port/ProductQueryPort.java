package com.solidvessel.payment.product.port;

import com.solidvessel.payment.product.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductQueryPort {

    List<Product> getProductsOfCart(Set<Long> productIds);

    boolean isAvailable(Long productId, int quantity);
}
