package com.microshop.paymentservice.services;

import com.microshop.paymentservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private final IRemoteRequestService requestService;

    public ProductService(final IRemoteRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void setPaymentIds(final Long paymentId, final List<Long> productIds) {
        requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/setPaymentIds")
                .withHttpMethod(HttpMethod.PUT)
                .withQueryParameter("paymentId", paymentId)
                .withQueryParameter("productIds", productIds)
                .withResponseType(String.class)
                .send();
    }
}