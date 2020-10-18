package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Payment;
import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private final IProductService productService;
    private final IAsyncRequestService requestService;

    public PaymentService(final IProductService productService, final IAsyncRequestService requestService) {
        this.productService = productService;
        this.requestService = requestService;
    }

    @Override
    public Payment getPaymentOfProduct(final Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return null;
        }
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + product.getPaymentId())
                .withResponseType(Payment.class)
                .send();
    }
}
