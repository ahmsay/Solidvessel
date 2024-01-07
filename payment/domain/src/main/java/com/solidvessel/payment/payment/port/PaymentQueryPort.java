package com.solidvessel.payment.payment.port;

import com.solidvessel.payment.payment.model.Payment;

import java.util.List;

public interface PaymentQueryPort {

    List<Payment> getAll();

    Payment getById(Long id);

    List<Payment> getByCustomerId(String customerId);
}
