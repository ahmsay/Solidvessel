package com.solidvessel.order.domain.order.datamodel;

public record OrderDataModel(Long id, String status, Long customerId, Long paymentId) {
}
