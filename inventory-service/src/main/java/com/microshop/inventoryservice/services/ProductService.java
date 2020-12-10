package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> findByPaymentId(final Long paymentId) {
        return productRepository.findByPaymentId(paymentId);
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public String setPaymentIds(final Long paymentId, final List<Long> productIds) {
        for (Long id : productIds) {
            Product product = findById(id);
            if (product != null) {
                product.setPaymentId(paymentId);
                productRepository.save(product);
            }
        }
        return "Payment ids are set to related products";
    }
}
