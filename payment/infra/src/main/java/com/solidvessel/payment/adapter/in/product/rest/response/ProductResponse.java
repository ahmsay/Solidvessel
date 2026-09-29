package com.solidvessel.payment.adapter.in.product.rest.response;

import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.payment.product.model.ProductSubcategory;

import java.io.Serializable;

public record ProductResponse(Long id, String name, Double price, ProductCategory category,
                              ProductSubcategory subcategory, Integer quantity) implements Serializable {

}
