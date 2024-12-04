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
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class UpdateProductQuantitiesCommandService implements CommandService<PaymentSavedEvent, Void> {

    private final ProductPort productPort;
    private final ProductQueryPort productQueryPort;
    private final EventPublisher<ProductsCheckedEvent> productsCheckedEventPublisher;

    @Override
    public Void execute(PaymentSavedEvent command) {
        var productQuantities = command.productQuantities();
        List<Product> products = productQueryPort.getByIds(productQuantities.keySet().stream().toList());
        products.forEach(product -> {
            var boughtQuantity = productQuantities.get(product.getId());
            var availability = product.isAvailable(boughtQuantity);
            if (availability.getIsAvailable()) {
                product.decreaseQuantity(boughtQuantity);
            } else {
                productsCheckedEventPublisher.publish(new ProductsCheckedEvent(command.paymentId(), false, command.customerId()));
                throw new InventoryDomainException("Products are not available.");
            }
        });
        productPort.saveProducts(products);
        productsCheckedEventPublisher.publish(new ProductsCheckedEvent(command.paymentId(), true, command.customerId()));
        return null;
    }
}
