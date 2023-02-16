package com.solidvessel.payment.domain.payment.datamodel;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;

public record PaymentDetailDataModel(Long id, Double totalCharge, CustomerDataModel customer,
                                     List<ProductDataModel> products) {

    public static PaymentDetailDataModel from(PaymentDataModel payment, CustomerDataModel customer, List<ProductDataModel> products) {
        return new PaymentDetailDataModel(
                payment.id(),
                payment.totalCharge(),
                customer,
                products
        );
    }
}
