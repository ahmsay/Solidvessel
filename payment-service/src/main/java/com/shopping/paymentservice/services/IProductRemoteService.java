package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Product;

import java.util.Set;

public interface IProductRemoteService {

    Set<Product> getProductsOfPayment(String paymentId);
}
