package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;

    public ProductService(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(final String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Set<Product> getProductsByIds(final Set<String> productIds) {
        return productIds.stream().map(this::getProductById).collect(Collectors.toSet());
    }
}
