package com.solidvessel.payment.payment.model;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Payment {

    private Long id;
    private String customerId;
    private List<Product> products;
    private Double totalPrice;
    private PaymentStatus status;

    public static Payment newPayment(String customerId, Cart cart) {
        return new Payment(
                null,
                customerId,
                cart.getProductList(),
                cart.getTotalPrice(),
                PaymentStatus.PENDING
        );
    }

    public void approve() {
        this.status = PaymentStatus.APPROVED;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELLED;
    }
}
