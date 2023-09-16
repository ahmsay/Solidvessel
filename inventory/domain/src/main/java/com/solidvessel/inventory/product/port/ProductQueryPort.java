package com.solidvessel.inventory.product.port;

import com.solidvessel.inventory.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.product.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductQueryPort {

    List<ProductDataModel> getAll();

    ProductDataModel getById(Long id);

    List<ProductDataModel> getByIds(List<Long> ids);

    List<Product> getByIds(Set<Long> ids);

    boolean isAvailable(Long id, int quantity);
}
