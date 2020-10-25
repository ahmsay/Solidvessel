package com.microshop.paymentservice.dto;

import java.util.List;

public class ProductDTO {

    private List<Long> ids;
    private Long paymentId;

    public ProductDTO() {};

    public ProductDTO(final List<Long> ids, final Long paymentId) {
        this.ids = ids;
        this.paymentId = paymentId;
    }

    public List<Long> getIds() {
        return ids;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
