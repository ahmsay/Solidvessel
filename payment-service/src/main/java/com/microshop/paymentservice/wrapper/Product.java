package com.microshop.paymentservice.wrapper;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private String category;

    public Product() {}

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
