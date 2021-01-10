package com.microshop.inventoryservice.dto;

public class ProductDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final String category;
    private PaymentDTO payment;

    public ProductDTO(final Long id, final String name, final Double price, final String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public ProductDTO(final Long id, final String name, final Double price, final String category, final PaymentDTO payment) {
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

    public PaymentDTO getPayment() {
        return payment;
    }
}
