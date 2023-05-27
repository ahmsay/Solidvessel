package com.solidvessel.payment.domain.payment.service;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductQuantityDomainService {

    public boolean areQuantitiesAvailable(Map<Long, Integer> productsInCart, List<ProductDataModel> productsFromInventory) {
        return productsFromInventory.stream()
                .noneMatch(productFromInventory -> productsInCart.get(productFromInventory.id()) > productFromInventory.quantity());
    }
}
