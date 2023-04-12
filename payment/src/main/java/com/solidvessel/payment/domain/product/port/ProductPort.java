package com.solidvessel.payment.domain.product.port;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;

public interface ProductPort {

    List<ProductDataModel> getProductsOfPayment(Long paymentId);
}
