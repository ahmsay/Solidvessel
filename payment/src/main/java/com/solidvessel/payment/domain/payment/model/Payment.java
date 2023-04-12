package com.solidvessel.payment.domain.payment.model;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private Long customerId;
    private List<ProductDataModel> products;

    public static Payment newPayment(Long customerId, List<ProductDataModel> products) {
        return new Payment(null, customerId, products);
    }
}
