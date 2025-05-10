package com.solidvessel.order.adapter.in.order.rest.request;

import com.solidvessel.order.order.service.command.UpdateDeliveryAddressCommand;

public record UpdateDeliveryAddressRequest(String address) {

    public UpdateDeliveryAddressCommand toCommand(Long id) {
        return new UpdateDeliveryAddressCommand(id, address);
    }
}
