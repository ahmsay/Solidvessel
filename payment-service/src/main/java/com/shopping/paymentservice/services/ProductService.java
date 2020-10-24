package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.ProductDTO;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements IProductService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private final IAsyncRequestService requestService;

    public ProductService(final IAsyncRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void updatePayments(final ProductDTO productDTO) {
        requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/setPayments")
                .withHttpMethod(HttpMethod.PUT)
                .withRequestBody(productDTO)
                .withResponseType(ProductDTO.class)
                .send();
    }
}
