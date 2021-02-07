package com.microshop.paymentservice.services;

import com.microshop.paymentservice.configuration.remote.IRequestService;
import com.microshop.paymentservice.configuration.remote.Services;
import com.microshop.paymentservice.dto.ProductDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IRequestService requestService;
    private final Services services;

    public ProductService(final IRequestService requestService, final Services services) {
        this.requestService = requestService;
        this. services = services;
    }

    @Override
    public List<ProductDTO> findByIds(final List<Long> productIds) {
        ProductDTO[] products = requestService.createRequest(services.getInventory())
                .toPath("/products/findByIds")
                .withHttpMethod(HttpMethod.GET)
                .withQueryParameter("productIds", productIds)
                .withResponseType(ProductDTO[].class)
                .send();
        return Arrays.asList(products);
    }
}
