package com.solidvessel.payment.payment.datamodel;

import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.product.datamodel.ProductDataModel;

import java.util.List;

public record PaymentDataModel(Long id, String customerId, List<ProductDataModel> products, Double totalCharge) {

    public static PaymentDataModel from(Payment payment) {
        return new PaymentDataModel(
                payment.getId(),
                payment.getCustomerId(),
                payment.getProducts().stream().map(ProductDataModel::from).toList(),
                payment.getTotalPrice()
        );
    }
}
