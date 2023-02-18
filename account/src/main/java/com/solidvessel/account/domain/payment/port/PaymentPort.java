package com.solidvessel.account.domain.payment.port;

import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;

import java.util.List;

public interface PaymentPort {

    List<PaymentDataModel> getPaymentsOfCustomer(Long customerId);
}
