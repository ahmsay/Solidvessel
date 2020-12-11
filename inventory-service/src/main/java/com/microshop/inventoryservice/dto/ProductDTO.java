package com.microshop.inventoryservice.dto;

import com.microshop.inventoryservice.entity.Payment;

public class ProductDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final String category;
    private final Payment payment;

    public ProductDTO(final Long id, final String name, final Double price, final String category, final Payment payment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Payment getPayment() {
        return payment;
    }
}
