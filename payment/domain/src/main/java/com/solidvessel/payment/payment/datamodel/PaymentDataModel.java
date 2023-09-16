package com.solidvessel.payment.payment.datamodel;

import com.solidvessel.payment.product.datamodel.ProductDataModel;

import java.util.List;

public record PaymentDataModel(Long id, Long customerId, List<ProductDataModel> products, Double totalCharge) {
}
