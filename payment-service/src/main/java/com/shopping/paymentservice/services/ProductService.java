package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.ProductDTO;
import com.shopping.paymentservice.remote.IRemoteRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void setPaymentIds(final ProductDTO productDTO) {
        requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/setPaymentIds")
                .withHttpMethod(HttpMethod.PUT)
                .withRequestBody(productDTO)
                .withResponseType(ProductDTO.class)
                .send();
    }
}
