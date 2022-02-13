package com.microshop.payment.request;

import com.microshop.payment.entity.Payment;

import java.util.List;

public record AddPaymentRequest(Payment payment, List<Long> productIds) {
}
