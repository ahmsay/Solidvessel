package com.microshop.paymentservice.services;

import com.microshop.paymentservice.configuration.remote.IRequestService;
import com.microshop.paymentservice.configuration.remote.URLs;
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
    private final URLs urls;

    public ProductService(final IRequestService requestService, final URLs urls) {
        this.requestService = requestService;
        this. urls = urls;
    }

    @Override
    public List<ProductDTO> findByPaymentId(final Long paymentId) {
        ProductDTO[] products = requestService.createRequest(urls.getInventory())
                .toPath("/products/ofPayment/" + paymentId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(ProductDTO[].class)
                .send();
        return Arrays.asList(products);
    }
}
