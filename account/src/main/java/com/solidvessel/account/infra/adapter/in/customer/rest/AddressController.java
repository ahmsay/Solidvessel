package com.solidvessel.account.infra.adapter.in.customer.rest;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;
import com.solidvessel.account.domain.customer.port.AddressQueryPort;
import com.solidvessel.account.domain.customer.service.command.AddAddressCommand;
import com.solidvessel.account.domain.customer.service.command.RemoveAddressCommand;
import com.solidvessel.account.domain.customer.service.command.UpdateAddressCommand;
import com.solidvessel.account.infra.adapter.in.customer.rest.request.AddAddressRequest;
import com.solidvessel.account.infra.adapter.in.customer.rest.request.RemoveAddressRequest;
import com.solidvessel.account.infra.adapter.in.customer.rest.request.UpdateAddressRequest;
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

    private final AddressQueryPort addressQueryPort;
    private final CommandService<AddAddressCommand> addAddressCommandService;
    private final CommandService<RemoveAddressCommand> removeAddressCommandService;
    private final CommandService<UpdateAddressCommand> updateAddressCommandService;

    @GetMapping()
    public List<AddressDataModel> getAddresses() {
        return addressQueryPort.getAddresses();
    }

    @PostMapping()
    public OperationResult addAddress(@RequestBody @Valid AddAddressRequest request) {
        return addAddressCommandService.execute(request.toCommand());
    }

    @DeleteMapping()
    public OperationResult removeAddress(@RequestBody @Valid RemoveAddressRequest request) {
        return removeAddressCommandService.execute(request.toCommand());
    }

    @PutMapping()
    public OperationResult updateAddress(@RequestBody @Valid UpdateAddressRequest request) {
        return updateAddressCommandService.execute(request.toCommand());
    }
}
