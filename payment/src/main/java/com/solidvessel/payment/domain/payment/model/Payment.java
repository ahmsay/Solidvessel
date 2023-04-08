package com.solidvessel.payment.domain.payment.model;

import com.solidvessel.payment.domain.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private Long customerId;
    private List<Product> products;
    private boolean accepted;

    public static Payment newPayment(Product product, Long customerId) {
        return new Payment(null, customerId, List.of(product), false);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
