package com.solidvessel.account.adapter.in.address.rest;

import com.solidvessel.account.adapter.in.address.rest.request.AddAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.DeleteAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.UpdateAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.response.AddressResponse;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.AddAddressCommandService;
import com.solidvessel.account.address.service.DeleteAddressCommandService;
import com.solidvessel.account.address.service.UpdateAddressCommandService;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
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
    private final AddAddressCommandService addAddressCommandService;
    private final DeleteAddressCommandService deleteAddressCommandService;
    private final UpdateAddressCommandService updateAddressCommandService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping
    public List<AddressResponse> getAddresses(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return addressQueryPort.getAddresses(SessionUtil.getCurrentUserId(), QueryOptions.of(pageNumber, pageSize)).stream().map(AddressResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping
    public AddressResponse addAddress(@RequestBody @Valid AddAddressRequest request) {
        return AddressResponse.from(addAddressCommandService.execute(request.toCommand()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping
    public OperationResult deleteAddress(@RequestBody @Valid DeleteAddressRequest request) {
        return deleteAddressCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping
    public AddressResponse updateAddress(@RequestBody @Valid UpdateAddressRequest request) {
        return AddressResponse.from(updateAddressCommandService.execute(request.toCommand()));
    }
}
