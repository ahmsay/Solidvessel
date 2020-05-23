package com.shopping.inventoryservice.entity;

public class Product {

    private int id;
    private String name;
    private String category;
    private int paymentId;

    public Product(final int id, final String name, final String category, final int paymentId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.paymentId = paymentId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final int paymentId) {
        this.paymentId = paymentId;
    }
}
