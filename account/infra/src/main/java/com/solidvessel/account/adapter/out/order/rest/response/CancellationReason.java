package com.solidvessel.account.adapter.out.order.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CancellationReason {

    DELIVERY_TOOK_TOO_LONG("The delivery took too long."),
    FOUND_BETTER_ALTERNATIVE("I found a better alternative."),
    DONT_NEED_ANYMORE("I don't need this product(s) anymore.");

    public final String reason;

    CancellationReason(String reason) {
        this.reason = reason;
    }

    @JsonValue
    @Override
    public String toString() {
        return reason;
    }

    @JsonCreator
    public static CancellationReason fromString(String value) {
        for (CancellationReason r : values()) {
            if (r.reason.equals(value) || r.name().equals(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown cancellation reason: " + value);
    }
}
