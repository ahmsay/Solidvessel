package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;

public interface PaymentPort {

    List<PaymentDataModel> getAll();

    PaymentDataModel getById(Long id);

    List<PaymentDataModel> getByCustomerId(Long customerId);

    Long save(Payment payment);

    List<ProductDataModel> getProductsOfPayment(Long paymentId);
}
