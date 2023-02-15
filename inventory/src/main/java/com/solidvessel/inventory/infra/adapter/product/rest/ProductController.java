package com.solidvessel.inventory.infra.adapter.product.rest;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.service.ProductCommandService;
import com.solidvessel.inventory.infra.adapter.product.rest.request.AddProductRequest;
import com.solidvessel.shared.infra.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductPort productPort;
    private final ProductCommandService productCommandService;

    public ProductController(final ProductPort productPort, ProductCommandService productCommandService) {
        this.productPort = productPort;
        this.productCommandService = productCommandService;
    }

    @GetMapping()
    public Response<List<ProductDataModel>> getAll() {
        return new Response<>(productPort.getAll());
    }

    @GetMapping("/{id}")
    public Response<ProductDataModel> getById(@PathVariable final Long id) {
        return new Response<>(productPort.getById(id));
    }

    @GetMapping("/ofPayment/{paymentId}")
    Response<List<ProductDataModel>> getByPaymentId(@PathVariable final Long paymentId) {
        return new Response<>(productPort.getByPaymentId(paymentId));
    }

    @PostMapping()
    public void add(@RequestBody final AddProductRequest request) {
        productCommandService.add(request.toCommand());
    }
}
