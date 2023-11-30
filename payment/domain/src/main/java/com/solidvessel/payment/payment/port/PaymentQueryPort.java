package com.solidvessel.payment.payment.port;

import com.solidvessel.payment.payment.datamodel.PaymentDataModel;

import java.util.List;

public interface PaymentQueryPort {

    List<PaymentDataModel> getAll();

    PaymentDataModel getById(Long id);

    List<PaymentDataModel> getByCustomerId(String customerId);
}
