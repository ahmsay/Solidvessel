package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Product;

import java.util.List;

public interface IProductRemoteService {

    List<Product> getProductsOfPayment(String paymentId);
}
