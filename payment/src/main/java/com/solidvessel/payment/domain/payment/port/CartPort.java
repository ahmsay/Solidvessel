package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.model.Payment;

public interface CartPort {

    boolean doesCartExist(Long customerId);

    Payment getByCustomerId(Long customerId);
}
