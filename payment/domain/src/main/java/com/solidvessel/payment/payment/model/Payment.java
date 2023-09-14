package com.solidvessel.payment.payment.model;

import com.solidvessel.payment.product.datamodel.ProductDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private Long customerId;
    private List<ProductDataModel> products;
    private Double totalPrice;

    public static Payment newPayment(Long customerId, List<ProductDataModel> productsFromInventory, Map<Long, Integer> productsInCart) {
        List<ProductDataModel> soldProducts = productsFromInventory.stream()
                .map(product -> new ProductDataModel(product.id(), productsInCart.get(product.id()), product.name(), product.price())).toList();
        return new Payment(
                null,
                customerId,
                soldProducts,
                soldProducts.stream().map(product -> product.price() * product.quantity()).reduce(0D, Double::sum)
        );
    }
}
