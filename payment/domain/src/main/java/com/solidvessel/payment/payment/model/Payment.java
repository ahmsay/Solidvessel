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

    public static Payment newPayment(String customerId, List<Product> productsFromInventory, Map<Long, Integer> productsInCart) {
        List<Product> soldProducts = productsFromInventory.stream()
                .map(product -> new Product(product.getId(), productsInCart.get(product.getId()), product.getName(), product.getPrice())).toList();
        return new Payment(
                null,
                customerId,
                soldProducts,
                soldProducts.stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0D, Double::sum)
        );
    }
}
