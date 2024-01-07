package com.solidvessel.payment.adapter.in.payment.rest.datamodel;

import com.solidvessel.payment.adapter.out.customer.rest.datamodel.CustomerDataModel;
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
