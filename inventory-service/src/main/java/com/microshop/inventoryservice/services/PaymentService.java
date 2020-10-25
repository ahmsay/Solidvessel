package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Payment;
import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private final IProductService productService;
    private final IRemoteRequestService requestService;

    public PaymentService(final IProductService productService, final IRemoteRequestService requestService) {
        this.productService = productService;
        this.requestService = requestService;
    }

    @Override
    public Payment findPaymentOfProduct(final Long productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return null;
        }
        return requestService.createRequest(paymentServiceUrl)
                .toPath("/payments/" + product.getPaymentId())
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Payment.class)
                .send();
    }
}
