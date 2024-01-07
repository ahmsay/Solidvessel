package com.solidvessel.account.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private String status;
    private Long paymentId;
}
