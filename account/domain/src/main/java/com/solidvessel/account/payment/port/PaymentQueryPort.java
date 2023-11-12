package com.solidvessel.account.payment.port;

import com.solidvessel.account.payment.datamodel.PaymentDataModel;

import java.util.List;

public interface PaymentQueryPort {

    List<PaymentDataModel> getPaymentsOfCustomer(String customerId);
}
