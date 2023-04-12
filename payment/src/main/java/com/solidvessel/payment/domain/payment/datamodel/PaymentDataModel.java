package com.solidvessel.payment.domain.payment.datamodel;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;

public record PaymentDataModel(Long id, Long customerId, List<ProductDataModel> products) {
}
