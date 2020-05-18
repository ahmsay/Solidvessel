package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private Set<Payment> payments;

    public PaymentRepository() {
        payments = new HashSet<>();
        payments.add(new Payment(1, 10.5));
        payments.add(new Payment(2, 200));
        payments.add(new Payment(3, 999.99));
        payments.add(new Payment(4, 55));
        payments.add(new Payment(5, 750));
    }

    @Override
    public Set<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment getPaymentById(final int id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findAny()
                .orElse(null);
    }
}
