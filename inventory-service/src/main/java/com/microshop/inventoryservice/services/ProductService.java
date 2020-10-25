package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.ProductDTO;
import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ProductDTO setPaymentIds(final ProductDTO productDTO) {
        for (Long id : productDTO.getIds()) {
            Product product = findById(id);
            if (product != null) {
                product.setPaymentId(productDTO.getPaymentId());
                productRepository.save(product);
            }
        }
        return productDTO;
    }
}
