package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;

import java.util.List;

public interface PaymentQueryPort {

    List<PaymentDataModel> getAll();

    PaymentDataModel getById(Long id);

    List<PaymentDataModel> getByCustomerId(Long customerId);
}
