package com.solidvessel.inventory.adapter.in.product.rest;

import com.solidvessel.inventory.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.inventory.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import com.solidvessel.shared.service.CommandService;
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
    private final CommandService<AddProductCommand> addProductCommandService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public List<ProductDataModel> getAll() {
        return productQueryPort.getAll().stream().map(ProductDataModel::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{id}")
    public ProductDataModel getById(@PathVariable final Long id) {
        return ProductDataModel.from(productQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ids")
    public List<ProductDataModel> getByIds(@RequestParam final List<Long> ids) {
        return productQueryPort.getByIds(ids).stream().map(ProductDataModel::from).toList();
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
}
