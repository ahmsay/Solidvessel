package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    @Override
    public Iterable<Product> findByIds(List<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }
}
