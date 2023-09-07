package com.solidvessel.inventory.infra.adapter.in.product.rest;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.port.ProductQueryPort;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;
import com.solidvessel.inventory.infra.adapter.in.product.rest.request.AddProductRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductQueryPort productQueryPort;
    private final CommandService<AddProductCommand> addProductCommandService;

    @GetMapping()
    public List<ProductDataModel> getAll() {
        return productQueryPort.getAll();
    }

    @GetMapping("/{id}")
    public ProductDataModel getById(@PathVariable final Long id) {
        return productQueryPort.getById(id);
    }

    @GetMapping("/ids")
    public List<ProductDataModel> getByIds(@RequestParam final List<Long> ids) {
        return productQueryPort.getByIds(ids);
    }

    @PostMapping()
    public OperationResult add(@RequestBody @Valid final AddProductRequest request) {
        return addProductCommandService.execute(request.toCommand());
    }

    @GetMapping("/available")
    public boolean isAvailable(@RequestParam final Long id, @RequestParam int quantity) {
        return productQueryPort.isAvailable(id, quantity);
    }
}
