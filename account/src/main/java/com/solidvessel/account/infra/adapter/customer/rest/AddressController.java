package com.solidvessel.account.infra.adapter.customer.rest;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;
import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.domain.customer.service.command.AddAddressCommand;
import com.solidvessel.account.domain.customer.service.command.RemoveAddressCommand;
import com.solidvessel.account.infra.adapter.customer.rest.request.AddAddressRequest;
import com.solidvessel.account.infra.adapter.customer.rest.request.RemoveAddressRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressPort addressPort;
    private final CommandService<AddAddressCommand> addAddressCommandCommandService;
    private final CommandService<RemoveAddressCommand> removeAddressCommandCommandService;

    @GetMapping()
    public List<AddressDataModel> getAddresses() {
        return addressPort.getAddresses();
    }

    @PostMapping()
    public OperationResult addAddress(@RequestBody @Valid AddAddressRequest request) {
        return addAddressCommandCommandService.execute(request.toCommand());
    }

    @DeleteMapping()
    public OperationResult removeAddress(@RequestBody @Valid RemoveAddressRequest request) {
        return removeAddressCommandCommandService.execute(request.toCommand());
    }
}
