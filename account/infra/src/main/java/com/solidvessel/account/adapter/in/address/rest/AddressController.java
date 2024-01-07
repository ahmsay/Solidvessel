package com.solidvessel.account.adapter.in.address.rest;

import com.solidvessel.account.adapter.in.address.rest.request.AddAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.RemoveAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.UpdateAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.response.AddressResponse;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.account.address.service.command.RemoveAddressCommand;
import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public List<AddressResponse> getAddresses() {
        return addressQueryPort.getAddresses(SessionUtil.getCurrentUserId()).stream().map(AddressResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping()
    public OperationResult addAddress(@RequestBody @Valid AddAddressRequest request) {
        return addAddressCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping()
    public OperationResult removeAddress(@RequestBody @Valid RemoveAddressRequest request) {
        return removeAddressCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping()
    public OperationResult updateAddress(@RequestBody @Valid UpdateAddressRequest request) {
        return updateAddressCommandService.execute(request.toCommand());
    }
}
