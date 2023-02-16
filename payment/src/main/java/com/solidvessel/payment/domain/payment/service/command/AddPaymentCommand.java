package com.solidvessel.payment.domain.payment.service.command;

import java.util.List;

public record AddPaymentCommand(Double totalCharge, Long customerId, List<Long> productIds) {
}
