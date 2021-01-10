package com.microshop.paymentservice.dto;

import java.util.List;

public class PaymentDTO {

    private final Long id;
    private final Double totalCharge;
    private CustomerDTO customer;
    private List<ProductDTO> productList;

    public PaymentDTO(final Long id, final Double totalCharge) {
        this.id = id;
        this.totalCharge = totalCharge;
    }

    public PaymentDTO(final Long id, final Double totalCharge, final CustomerDTO customer, final List<ProductDTO> productList) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customer = customer;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }
}
