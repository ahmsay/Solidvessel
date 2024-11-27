package com.solidvessel.payment.adapter.in.cart.rest;

import com.solidvessel.payment.adapter.in.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.payment.adapter.in.cart.rest.response.CartResponse;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.RemoveFromCartCommandService;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final RemoveFromCartCommandService removeFromCartCommandService;
    private final CartQueryPort cartQueryPort;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping
    public CartResponse listCart() {
        return CartResponse.from(cartQueryPort.getByCustomerId(SessionUtil.getCurrentUserId()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping()
    public OperationResult removeFromCart(@RequestBody @Valid RemoveFromCartRequest request) {
        return removeFromCartCommandService.execute(request.toCommand());
    }
}
