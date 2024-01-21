package com.solidvessel.inventory.product.service;

import com.solidvessel.inventory.common.exception.InventoryDomainException;
import com.solidvessel.inventory.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.product.event.ProductsCheckedEvent;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class UpdateProductQuantitiesCommandService implements CommandService<PaymentSavedEvent> {

    private final ProductPort productPort;
    private final ProductQueryPort productQueryPort;
    private final EventPublisher<ProductsCheckedEvent> productsCheckedEventPublisher;

    @Override
    public OperationResult execute(PaymentSavedEvent command) {
        var productQuantities = command.productQuantities();
        List<Product> products = productQueryPort.getByIds(productQuantities.keySet().stream().toList());
        products.forEach(product -> {
            int boughtQuantity = productQuantities.get(product.getId());
            if (product.isAvailable(boughtQuantity)) {
                product.decreaseQuantity(boughtQuantity);
            } else {
                productsCheckedEventPublisher.publish(new ProductsCheckedEvent(command.paymentId(), false, command.customerId()));
                throw new InventoryDomainException("Products are not available in stocks.");
            }
        });
        productPort.saveProducts(products);
        productsCheckedEventPublisher.publish(new ProductsCheckedEvent(command.paymentId(), true, command.customerId()));
        return new OperationResult("Product quantities are updated.", ResultType.SUCCESS);
    }
}
