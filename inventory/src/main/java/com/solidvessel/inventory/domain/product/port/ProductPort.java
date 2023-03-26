package com.solidvessel.inventory.domain.product.port;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;

import java.util.List;

public interface ProductPort {

    List<ProductDataModel> getAll();

    ProductDataModel getById(Long id);

    List<ProductDataModel> getByPaymentId(Long paymentId);

    void add(Product product);
}