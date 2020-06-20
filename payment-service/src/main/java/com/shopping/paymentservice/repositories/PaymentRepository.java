package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        payments = new ArrayList<>();
        payments.add(new Payment("1", 10.5, "1", new ArrayList<>(Arrays.asList("1", "2", "3")), "1"));
        payments.add(new Payment("2", 200, "2", new ArrayList<>(Collections.singletonList("4")), "2"));
        payments.add(new Payment("3", 999.99, "2", new ArrayList<>(Arrays.asList("5", "6")), "3"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment getPaymentById(final String id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
