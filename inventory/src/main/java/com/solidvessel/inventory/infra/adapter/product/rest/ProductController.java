package com.solidvessel.inventory.infra.adapter.product.rest;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;
import com.solidvessel.inventory.infra.adapter.product.rest.request.AddProductRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductPort productPort;
    private final CommandService<AddProductCommand> addProductCommandService;

    @GetMapping()
    public List<ProductDataModel> getAll() {
        return productPort.getAll();
    }

    @GetMapping("/{id}")
    public ProductDataModel getById(@PathVariable final Long id) {
        return productPort.getById(id);
    }

    @GetMapping("/ids")
    public List<ProductDataModel> getByIds(@RequestParam final List<Long> ids) {
        return productPort.getByIds(ids);
    }

    @PostMapping()
    public OperationResult add(@RequestBody final AddProductRequest request) {
        return addProductCommandService.execute(request.toCommand());
    }

    @GetMapping("/{id}/available")
    public boolean isAvailable(@PathVariable final Long id) {
        return productPort.isAvailable(id);
    }
}
