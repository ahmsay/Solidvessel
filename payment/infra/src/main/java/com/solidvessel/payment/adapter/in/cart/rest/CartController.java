package com.solidvessel.payment.adapter.in.cart.rest;

import com.solidvessel.payment.adapter.in.cart.rest.request.AddToCartRequest;
import com.solidvessel.payment.adapter.in.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.payment.cart.datamodel.CartDataModel;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.product.datamodel.ProductDataModel;
import com.solidvessel.payment.product.port.ProductQueryPort;
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
@RequestMapping("/cart")
public class CartController {

    private final CommandService<AddToCartCommand> addToCartCommandService;
    private final CommandService<RemoveFromCartCommand> removeFromCartCommandService;
    private final CartQueryPort cartQueryPort;
    private final ProductQueryPort productQueryPort;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping()
    public OperationResult addToCart(@RequestBody @Valid final AddToCartRequest request) {
        return addToCartCommandService.execute(request.toCommand());
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping
    public CartDataModel listCart() {
        Cart cart = cartQueryPort.getByCustomerId(SessionUtil.getCurrentUserId());
        List<ProductDataModel> products = productQueryPort.getProductsOfCart(cart.getProductQuantities().keySet());
        return CartDataModel.from(cart, products);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping()
    public OperationResult removeFromCart(@RequestBody @Valid final RemoveFromCartRequest request) {
        return removeFromCartCommandService.execute(request.toCommand());
    }
}
