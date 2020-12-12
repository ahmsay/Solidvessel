package com.microshop.paymentservice.dto;

import com.microshop.paymentservice.entity.Customer;
import com.microshop.paymentservice.entity.Product;

import java.util.List;

public class PaymentDTO {

    private final Long id;
    private final Double totalCharge;
    private final Customer customer;
    private final List<Product> productList;

    public PaymentDTO(final Long id, final Double totalCharge, final Customer customer, final List<Product> productList) {
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

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
