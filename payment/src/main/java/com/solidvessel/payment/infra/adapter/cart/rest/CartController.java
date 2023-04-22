package com.solidvessel.payment.infra.adapter.cart.rest;

import com.solidvessel.payment.domain.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.domain.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.infra.adapter.cart.rest.request.AddToCartRequest;
import com.solidvessel.payment.infra.adapter.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CommandService<AddToCartCommand> addToCartCommandService;
    private final CommandService<RemoveFromCartCommand> removeFromCartCommandService;

    @PostMapping()
    public OperationResult addToCart(@RequestBody @Valid final AddToCartRequest request) {
        return addToCartCommandService.execute(request.toCommand());
    }

    @DeleteMapping()
    public OperationResult removeFromCart(@RequestBody @Valid final RemoveFromCartRequest request) {
        return removeFromCartCommandService.execute(request.toCommand());
    }
}
