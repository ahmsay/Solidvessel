package com.solidvessel.payment.domain.payment.datamodel;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;

public record PaymentDetailDataModel(PaymentDataModel payment, CustomerDataModel customer) {

    public static PaymentDetailDataModel from(PaymentDataModel payment, CustomerDataModel customer) {
        return new PaymentDetailDataModel(
                payment,
                customer
        );
    }
}
