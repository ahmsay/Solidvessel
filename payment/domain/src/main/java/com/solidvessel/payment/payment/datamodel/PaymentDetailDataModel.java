package com.solidvessel.payment.payment.datamodel;

import com.solidvessel.payment.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.payment.model.Payment;

public record PaymentDetailDataModel(PaymentDataModel payment, CustomerDataModel customer) {

    public static PaymentDetailDataModel from(Payment payment, Customer customer) {
        return new PaymentDetailDataModel(
                PaymentDataModel.from(payment),
                CustomerDataModel.from(customer)
        );
    }
}
