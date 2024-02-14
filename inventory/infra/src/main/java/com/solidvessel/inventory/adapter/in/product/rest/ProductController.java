package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductToCartRequest;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.AddProductCommandService;
import com.solidvessel.inventory.product.service.AddProductToCartCommandService;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductQueryPort productQueryPort;
    private final AddProductCommandService addProductCommandService;
    private final AddProductToCartCommandService addProductToCartCommandService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public List<ProductResponse> getProducts(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return productQueryPort.getProducts(QueryOptions.of(pageNumber, pageSize)).stream().map(ProductResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable final Long id) {
        return ProductResponse.from(productQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ids")
    public List<ProductResponse> getByIds(@RequestParam final List<Long> ids) {
        return productQueryPort.getByIds(ids).stream().map(ProductResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping()
    public OperationResult add(@RequestBody @Valid final AddProductRequest request) {
        return addProductCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/isAvailable")
    public boolean isAvailable(@RequestParam final Long id, @RequestParam int quantity) {
        return productQueryPort.isAvailable(id, quantity);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/{id}/addToCart")
    public OperationResult addToCart(@PathVariable final Long id, @RequestBody @Valid final AddProductToCartRequest request) {
        return addProductToCartCommandService.execute(request.toCommand(id));
    }
}
