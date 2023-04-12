package com.solidvessel.payment.domain.product.port;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;
import java.util.Set;

public interface ProductRestPort {

    List<ProductDataModel> getProductsOfCart(Set<Long> productIds);

    boolean isAvailable(Long productId);
}
