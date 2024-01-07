package com.solidvessel.payment.product.service;

import com.solidvessel.payment.product.model.Product;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@DomainComponent
@RequiredArgsConstructor
public class ProductQuantityDomainService {

    public boolean areQuantitiesAvailable(Map<Long, Integer> productsInCart, List<Product> productsFromInventory) {
        return productsFromInventory.stream()
                .noneMatch(productFromInventory -> productsInCart.get(productFromInventory.getId()) > productFromInventory.getQuantity());
    }
}
