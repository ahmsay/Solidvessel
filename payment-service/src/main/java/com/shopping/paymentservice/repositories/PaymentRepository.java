package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private Set<Payment> payments;

    public PaymentRepository() {
        payments = new HashSet<>();
        payments.add(new Payment(1, 10.5, 1, new HashSet<>(Arrays.asList(1, 2, 3)), 1));
        payments.add(new Payment(2, 200, 2, new HashSet<>(Collections.singletonList(4)), 2));
        payments.add(new Payment(3, 999.99, 2, new HashSet<>(Arrays.asList(5, 6)), 3));
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
