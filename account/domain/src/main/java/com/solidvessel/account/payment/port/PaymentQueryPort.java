package com.solidvessel.account.payment.port;

import com.solidvessel.account.payment.model.Payment;

import java.util.List;

public interface PaymentQueryPort {

    List<Payment> getPaymentsOfCustomer(String customerId);
}
