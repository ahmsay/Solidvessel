package com.solidvessel.payment.infra.adapter.in.payment.rest;

import com.solidvessel.payment.domain.payment.service.AcceptPaymentCommand;
import com.solidvessel.shared.infra.util.SessionUtil;

public record AcceptPaymentRequest() {

    public AcceptPaymentCommand toCommand() {
        return new AcceptPaymentCommand(SessionUtil.getCurrentUserId());
    }
}
