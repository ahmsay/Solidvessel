package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.adapter.out.product.db.repository.ProductRepository;
import com.solidvessel.inventory.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ProductDBQueryAdapter implements ProductQueryPort {

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
    public boolean isAvailable(Long id, int quantity) {
        return productRepository.findByIdAndQuantityGreaterThanEqual(id, quantity).isPresent();
    }

    @Override
    public List<Product> getByIds(Set<Long> ids) {
        return productRepository.findAllById(ids).stream().map(ProductJpaEntity::toDomainModel).toList();
    }
}
