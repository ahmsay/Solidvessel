package com.solidvessel.order.adapter.out.payment.rest.response;

import java.io.Serializable;

public record PaymentResponse(Long id, Double totalCharge) implements Serializable {
}
