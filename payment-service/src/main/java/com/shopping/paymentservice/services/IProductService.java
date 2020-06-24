package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProductsOfPayment(String paymentId);
}
