package com.solidvessel.inventory.product.model;

public enum UnavailableReason {

    NOT_IN_STOCKS("The product is not available in stocks with your desired quantity."),
    NOT_AVAILABLE_IN_REGION("The product is not available in your region.");

    public final String reason;

    UnavailableReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return reason;
    }
}
