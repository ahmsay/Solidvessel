package com.microshop.paymentservice.request;

import com.microshop.paymentservice.entity.Payment;

import java.util.List;

public record SavePaymentRequest(Payment payment, List<Long> productIds) {
}
