package com.solidvessel.payment.infra.adapter.payment.rest.request;

import com.solidvessel.payment.domain.payment.service.command.AddPaymentCommand;

import java.util.List;

public record AddPaymentRequest(Double totalCharge, Long customerId, List<Long> productIds) {

    public AddPaymentCommand toCommand() {
        return new AddPaymentCommand(totalCharge, customerId, productIds);
    }
}
