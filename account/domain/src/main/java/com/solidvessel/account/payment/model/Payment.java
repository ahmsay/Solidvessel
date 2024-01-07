package com.solidvessel.account.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private Double totalCharge;
}
