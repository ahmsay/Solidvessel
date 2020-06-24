package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.entity.Product;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductRemoteService implements IProductRemoteService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private IPaymentService paymentService;
    private IAsyncRequestService requestService;

    public ProductRemoteService(final IPaymentService paymentService, final IAsyncRequestService requestService) {
        this.paymentService = paymentService;
        this.requestService = requestService;
    }

    @Override
    public List<Product> getProductsOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return null;
        }
        Product[] products = requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/withIds")
                .withQueryParameter("productIds", String.join(",", payment.getProductIds()))
                .withResponseType(Product[].class)
                .send();
        return Arrays.asList(products);
    }
}
