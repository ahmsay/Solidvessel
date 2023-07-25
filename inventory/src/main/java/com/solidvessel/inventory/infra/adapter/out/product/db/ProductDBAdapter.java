package com.solidvessel.inventory.infra.adapter.out.product.db;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.infra.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.infra.adapter.out.product.db.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDBAdapter implements ProductPort {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDataModel> getAll() {
        return productRepository.findAll().stream().map(ProductJpaEntity::toDataModel).toList();
    }

    @Override
    public ProductDataModel getById(Long id) {
        ProductJpaEntity productJpaEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        return productJpaEntity.toDataModel();
    }

    @Override
    public List<ProductDataModel> getByIds(List<Long> ids) {
        return productRepository.findAllById(ids).stream().map(ProductJpaEntity::toDataModel).toList();
    }

    @Override
    public void save(Product product) {
        productRepository.save(ProductJpaEntity.from(product));
    }

    @Override
    public boolean isAvailable(Long id, int quantity) {
        return productRepository.findByIdAndQuantityGreaterThanEqual(id, quantity).isPresent();
    }
}
