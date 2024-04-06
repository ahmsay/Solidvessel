package com.solidvessel.account.adapter.out.order.rest.response;

import java.io.Serializable;

public record OrderResponse(Long id, String status, Long paymentId) implements Serializable {
}
