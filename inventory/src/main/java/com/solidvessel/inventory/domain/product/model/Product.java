package com.solidvessel.inventory.domain.product.model;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private Long paymentId;

    public Product(Long id, String name, Double price, String category, Long paymentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.paymentId = paymentId;
    }

    public Product(String name, Double price, String category, Long paymentId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.paymentId = paymentId;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
