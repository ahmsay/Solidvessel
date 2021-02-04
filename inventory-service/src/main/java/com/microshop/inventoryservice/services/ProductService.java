package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.PaymentDTO;
import com.microshop.inventoryservice.dto.ProductDTO;
import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final IPaymentService paymentService;

    public ProductService(final IProductRepository productRepository, final IPaymentService paymentService) {
        this.productRepository = productRepository;
        this.paymentService = paymentService;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO findById(final Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        PaymentDTO payment = product.getPaymentId() == null ? null : paymentService.findById(product.getPaymentId());
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory(), payment);
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }
}
