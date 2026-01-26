package com.solidvessel.payment.adapter.in.product.rest.response;

import com.solidvessel.payment.product.model.ProductCategory;

import java.io.Serializable;

public record ProductResponse(Long id, String name, Double price, ProductCategory category,
                              Integer quantity) implements Serializable {
}
