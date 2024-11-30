package com.solidvessel.inventory.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductAvailability {

    private Boolean isAvailable;
    private UnavailableReason unavailableReason;

    public static ProductAvailability available() {
        return new ProductAvailability(true, null);
    }

    public static ProductAvailability notAvailableInRegion() {
        return new ProductAvailability(false, UnavailableReason.NOT_AVAILABLE_IN_REGION);
    }

    public static ProductAvailability notInStocks() {
        return new ProductAvailability(false, UnavailableReason.NOT_IN_STOCKS);
    }
}
