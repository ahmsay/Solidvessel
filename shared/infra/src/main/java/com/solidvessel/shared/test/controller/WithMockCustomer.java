package com.solidvessel.shared.test.controller;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(authorities = {"CUSTOMER"}, username = "123")
public @interface WithMockCustomer {
}
