package com.solidvessel.payment.payment.datamodel;

import com.solidvessel.payment.customer.datamodel.CustomerDataModel;

public record PaymentDetailDataModel(PaymentDataModel payment, CustomerDataModel customer) {

    public static PaymentDetailDataModel from(PaymentDataModel payment, CustomerDataModel customer) {
        return new PaymentDetailDataModel(
                payment,
                customer
        );
    }
}
