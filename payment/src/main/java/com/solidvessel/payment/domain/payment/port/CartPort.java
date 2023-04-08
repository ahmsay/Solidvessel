package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.model.Payment;

import java.util.Optional;

public interface CartPort {

    Optional<Payment> getByCustomerId(Long customerId);

    void save(Payment cart);
}
