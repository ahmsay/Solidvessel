package com.solidvessel.payment.adapter.in.payment.rest.request;

import com.solidvessel.payment.payment.service.command.AcceptPaymentCommand;
import com.solidvessel.shared.security.SessionUtil;

public record AcceptPaymentRequest() {

    public AcceptPaymentCommand toCommand() {
        return new AcceptPaymentCommand(SessionUtil.getCurrentUserId());
    }
}
