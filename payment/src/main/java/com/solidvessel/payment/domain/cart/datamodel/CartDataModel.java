package com.solidvessel.payment.domain.cart.datamodel;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;

public record CartDataModel(Long id, Long customerId, List<ProductDataModel> products) {
}
