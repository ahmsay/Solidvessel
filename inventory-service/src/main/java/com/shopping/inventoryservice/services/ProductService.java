package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.dto.ProductDTO;
import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsOfPayment(final Long paymentId) {
        return productRepository.findByPaymentId(paymentId);
    }

    @Override
    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductDTO setPayments(final ProductDTO productDTO) {
        for (Long id : productDTO.getIds()) {
            Product product = getProductById(id);
            if (product != null) {
                product.setPaymentId(productDTO.getPaymentId());
                productRepository.save(product);
            }
        }
        return productDTO;
    }
}
