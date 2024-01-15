package com.solidvessel.payment.payment.model;

import com.solidvessel.payment.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private String customerId;
    private List<Product> products;
    private Double totalPrice;

    public static Payment newPayment(String customerId, Map<Long, Product> products) {
        return new Payment(
                null,
                customerId,
                products.values().stream().toList(),
                products.values().stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0D, Double::sum)
        );
    }
}
