package com.microshop.paymentservice.wrapper;

public class Customer {

    private Long id;
    private String name;

    public Customer() {}

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
