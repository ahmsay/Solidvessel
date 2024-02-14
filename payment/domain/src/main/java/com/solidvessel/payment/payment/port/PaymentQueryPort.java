package com.solidvessel.payment.payment.port;

import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.shared.query.QueryOptions;

import java.util.List;

public interface PaymentQueryPort {

    List<Payment> getPayments(QueryOptions queryOptions);

    Payment getById(Long id);

    List<Payment> getByCustomerId(String customerId);
}
