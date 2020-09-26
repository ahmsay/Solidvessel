package com.shopping.inventoryservice.entity;

public class Product {

    private long id;
    private String name;
    private double price;
    private String category;
    private long paymentId;

    public Product(final long id, final String name, final double price, final String category, final long paymentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.paymentId = paymentId;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final long paymentId) {
        this.paymentId = paymentId;
    }
}
