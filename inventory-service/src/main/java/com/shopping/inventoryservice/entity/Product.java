package com.shopping.inventoryservice.entity;

public class Product {

    private String id;
    private String name;
    private String category;
    private String paymentId;

    public Product(final String id, final String name, final String category, final String paymentId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.paymentId = paymentId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }
}
