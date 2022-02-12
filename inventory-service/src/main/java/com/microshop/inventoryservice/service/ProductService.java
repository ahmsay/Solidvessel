package com.microshop.inventoryservice.service;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    public List<Product> getByIds(List<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

    public Product add(final Product product) {
        return productRepository.save(product);
    }
}
