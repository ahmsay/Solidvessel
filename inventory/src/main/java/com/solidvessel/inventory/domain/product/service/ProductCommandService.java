package com.solidvessel.inventory.domain.product.service;

import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandService {

    private final ProductPort productPort;

    public void add(AddProductCommand command) {
        productPort.add(new Product(command.name(), command.price(), command.category(), command.paymentId()));
    }

    public void updateSoldProducts(final Long paymentId, final List<Long> productIds) {
        productIds.forEach(productId -> {
            Product product = productPort.getById(productId).toDomainModel();
            product.setPaymentId(paymentId);
            productPort.add(product);
        });
    }
}
