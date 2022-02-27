package com.microshop.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    private Long id;

    public AppUser() {
    }

    public AppUser(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
