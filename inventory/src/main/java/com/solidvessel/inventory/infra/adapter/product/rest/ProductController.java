package com.solidvessel.inventory.infra.adapter.product.rest;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.service.ProductCommandService;
import com.solidvessel.inventory.infra.adapter.product.rest.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductPort productPort;
    private final ProductCommandService productCommandService;

    @GetMapping()
    public List<ProductDataModel> getAll() {
        return productPort.getAll();
    }

    @GetMapping("/{id}")
    public ProductDataModel getById(@PathVariable final Long id) {
        return productPort.getById(id);
    }

    @GetMapping("/ofPayment/{paymentId}")
    public List<ProductDataModel> getByPaymentId(@PathVariable final Long paymentId) {
        return productPort.getByPaymentId(paymentId);
    }

    @PostMapping()
    public void add(@RequestBody final AddProductRequest request) {
        productCommandService.add(request.toCommand());
    }
}
