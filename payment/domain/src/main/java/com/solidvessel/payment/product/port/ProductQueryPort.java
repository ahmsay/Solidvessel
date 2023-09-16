package com.solidvessel.payment.product.port;

import com.solidvessel.payment.product.datamodel.ProductDataModel;

import java.util.List;
import java.util.Set;

public interface ProductQueryPort {

    List<ProductDataModel> getProductsOfCart(Set<Long> productIds);

    boolean isAvailable(Long productId, int quantity);
}
