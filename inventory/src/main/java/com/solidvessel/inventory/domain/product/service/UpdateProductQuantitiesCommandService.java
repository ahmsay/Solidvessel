package com.solidvessel.inventory.domain.product.service;

import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.domain.product.port.ProductQueryPort;
import com.solidvessel.inventory.domain.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateProductQuantitiesCommandService implements CommandService<UpdateProductQuantitiesCommand> {

    private final ProductPort productPort;
    private final ProductQueryPort productQueryPort;

    @Override
    public OperationResult execute(UpdateProductQuantitiesCommand command) {
        var productQuantities = command.productQuantities();
        List<Product> products = productQueryPort.getByIds(productQuantities.keySet());
        products.forEach(product -> {
            int boughtQuantity = productQuantities.get(product.getId());
            product.decreaseQuantity(boughtQuantity);
        });
        productPort.saveProducts(products);
        return new OperationResult("Product quantities are updated.", ResultType.SUCCESS);
    }
}