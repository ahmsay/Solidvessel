package com.solidvessel.payment.domain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private Double totalCharge;
    private Long customerId;

    public Payment(Double totalCharge, Long customerId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }
}
