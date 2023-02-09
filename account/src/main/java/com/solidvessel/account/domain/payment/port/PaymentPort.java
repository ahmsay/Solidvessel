package com.solidvessel.account.domain.payment.port;

import com.solidvessel.account.infra.adapter.payment.rest.response.PaymentsResponse;

public interface PaymentPort {

    PaymentsResponse getPaymentsOfCustomer(Long customerId, String session);
}
