package com.solidvessel.account.adapter.out.payment.rest.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PaymentResponse(Long id, Double totalCharge, PaymentStatus status, LocalDateTime creationDate
) implements Serializable {
}
