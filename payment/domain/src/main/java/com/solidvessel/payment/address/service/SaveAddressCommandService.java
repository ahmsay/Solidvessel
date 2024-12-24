package com.solidvessel.payment.address.service;

import com.solidvessel.payment.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerPort;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.VoidCommandService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class SaveAddressCommandService implements VoidCommandService<PrimaryAddressSavedEvent> {

    private final CustomerPort customerPort;
    private final CustomerQueryPort customerQueryPort;

    @Override
    public void execute(PrimaryAddressSavedEvent event) {
        Optional<Customer> customerOptional = customerQueryPort.getById(event.customerId());
        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customer.updateAddress(event.address());
            customerPort.save(customer);
        } else {
            customerPort.save(event.toDomainModel());
        }
    }
}
