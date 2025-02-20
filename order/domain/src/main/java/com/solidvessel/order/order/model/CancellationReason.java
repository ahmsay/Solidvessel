package com.solidvessel.order.order.model;

public enum CancellationReason {

    DELIVERY_TOOK_TOO_LONG("The delivery took too long."),
    FOUND_BETTER_ALTERNATIVE("I found a better alternative."),
    DONT_NEED_ANYMORE("I don't need this product(s) anymore.");

    public final String reason;

    CancellationReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return reason;
    }
}
