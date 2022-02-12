package com.microshop.paymentservice.dto;

import com.microshop.paymentservice.entity.Payment;

import java.util.List;

public record SavePaymentDTO(Payment payment, List<Long> productIds) {
}
