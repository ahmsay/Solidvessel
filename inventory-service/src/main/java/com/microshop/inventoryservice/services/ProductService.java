package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import com.microshop.inventoryservice.wrapper.Payment;
import com.microshop.inventoryservice.wrapper.ProductDTO;
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
        Payment payment = paymentService.findById(product.getPaymentId());
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory(), payment);
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
    public void setPaymentIds(final Long paymentId, final List<Long> productIds) {
        for (Long id : productIds) {
            Product product = productRepository.findById(id).orElse(null);
            if (product != null) {
                product.setPaymentId(paymentId);
                productRepository.save(product);
            }
        }
    }
}
