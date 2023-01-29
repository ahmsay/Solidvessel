package com.solidvessel.payment.request;

import com.solidvessel.payment.entity.Payment;

import java.util.List;

public record AddPaymentRequest(Payment payment, List<Long> productIds) {
}
