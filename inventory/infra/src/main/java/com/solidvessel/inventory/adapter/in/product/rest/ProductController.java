package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductToCartRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.ChangeProductAvailabilityRequest;
import com.solidvessel.inventory.adapter.in.product.rest.request.UpdateProductRequest;
import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.ProductAvailability;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.*;
import com.solidvessel.inventory.product.service.command.DeleteProductCommand;
import com.solidvessel.inventory.product.service.command.DeleteProductsCommand;
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
    private final DeleteProductCommandService deleteProductCommandService;
    private final DeleteProductsCommandService deleteProductsCommandService;
    private final UpdateProductCommandService updateProductCommandService;
    private final ChangeProductAvailabilityCommandService changeProductAvailabilityCommandService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping
    public List<ProductResponse> getProducts(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return productQueryPort.getProducts(QueryOptions.of(pageNumber, pageSize)).stream().map(ProductResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return ProductResponse.from(productQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ids")
    public List<ProductResponse> getByIds(@RequestParam List<Long> ids) {
        return productQueryPort.getByIds(ids).stream().map(ProductResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public ProductResponse add(@RequestBody @Valid AddProductRequest request) {
        return ProductResponse.from(addProductCommandService.execute(request.toCommand()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{id}/isAvailable")
    public ProductAvailability isAvailable(@PathVariable Long id, @RequestParam Integer quantity) {
        return productQueryPort.getById(id).isAvailable(quantity);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/changeAvailability")
    public ProductAvailability changeAvailability(@RequestBody @Valid ChangeProductAvailabilityRequest request) {
        return changeProductAvailabilityCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping
    public ProductResponse update(@RequestBody @Valid UpdateProductRequest request) {
        return ProductResponse.from(updateProductCommandService.execute(request.toCommand()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/{id}/addToCart")
    public OperationResult addToCart(@PathVariable Long id, @RequestBody @Valid AddProductToCartRequest request) {
        return addProductToCartCommandService.execute(request.toCommand(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public OperationResult deleteById(@PathVariable Long id) {
        return deleteProductCommandService.execute(new DeleteProductCommand(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/ids")
    public OperationResult deleteByIds(@RequestParam List<Long> ids) {
        return deleteProductsCommandService.execute(new DeleteProductsCommand(ids));
    }
}
