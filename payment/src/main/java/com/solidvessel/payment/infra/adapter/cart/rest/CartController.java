package com.solidvessel.payment.infra.adapter.cart.rest;

import com.solidvessel.payment.domain.cart.datamodel.CartDataModel;
import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.domain.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductPort;
import com.solidvessel.payment.infra.adapter.cart.rest.request.AddToCartRequest;
import com.solidvessel.payment.infra.adapter.cart.rest.request.RemoveFromCartRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CommandService<AddToCartCommand> addToCartCommandService;
    private final CommandService<RemoveFromCartCommand> removeFromCartCommandService;
    private final CartPort cartPort;
    private final ProductPort productPort;

    @PostMapping()
    public OperationResult addToCart(@RequestBody @Valid final AddToCartRequest request) {
        return addToCartCommandService.execute(request.toCommand());
    }

    @GetMapping
    public CartDataModel listCart() {
        Cart cart = cartPort.getByCustomerId(SessionUtil.getCurrentUserId());
        List<ProductDataModel> products = productPort.getProductsOfCart(cart.getProducts().keySet());
        return new CartDataModel(cart.getId(), cart.getCustomerId(), products);
    }

    @DeleteMapping()
    public OperationResult removeFromCart(@RequestBody @Valid final RemoveFromCartRequest request) {
        return removeFromCartCommandService.execute(request.toCommand());
    }
}
