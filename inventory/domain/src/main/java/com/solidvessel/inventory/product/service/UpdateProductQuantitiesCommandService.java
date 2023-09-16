package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.inventory.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
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
