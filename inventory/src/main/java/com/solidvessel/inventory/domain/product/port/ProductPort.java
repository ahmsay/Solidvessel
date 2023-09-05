package com.solidvessel.inventory.domain.product.port;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductPort {

    List<ProductDataModel> getAll();

    ProductDataModel getById(Long id);

    List<ProductDataModel> getByIds(List<Long> ids);

    List<Product> getByIds(Set<Long> ids);

    void save(Product product);

    boolean isAvailable(Long id, int quantity);

    void saveProducts(List<Product> products);
}
