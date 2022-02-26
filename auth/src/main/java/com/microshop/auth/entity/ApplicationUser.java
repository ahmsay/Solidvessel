package com.microshop.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    public ApplicationUser() {
    }

    public ApplicationUser(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
