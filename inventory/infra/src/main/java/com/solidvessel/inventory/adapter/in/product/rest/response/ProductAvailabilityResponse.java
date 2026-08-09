package com.solidvessel.inventory.adapter.in.product.rest.response;

import com.solidvessel.inventory.product.model.UnavailableReason;

public record ProductAvailabilityResponse(Boolean isAvailable, UnavailableReason unavailableReason) {
}
