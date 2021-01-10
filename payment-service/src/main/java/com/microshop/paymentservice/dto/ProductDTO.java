package com.microshop.paymentservice.dto;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private String category;

    public ProductDTO() {}

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
}
